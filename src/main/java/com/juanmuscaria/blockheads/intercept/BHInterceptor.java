package com.juanmuscaria.blockheads.intercept;

import com.juanmuscaria.blockheads.NativeHelper;
import com.juanmuscaria.blockheads.network.InterceptedPacket;
import com.juanmuscaria.blockheads.network.Side;
import com.juanmuscaria.blockheads.network.packets.Packet;
import com.juanmuscaria.blockheads.network.packets.PacketRegistry;
import com.juanmuscaria.foreign.enet.ENet;
import com.juanmuscaria.foreign.enet.ENetAddress;
import com.juanmuscaria.foreign.enet.ENetEvent;
import com.juanmuscaria.foreign.enet.ENetPacket;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.HexFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Proxy blockheads connection and inspect what is going on under the hood
 */
@Command(name = "interceptor", mixinStandardHelpOptions = true, description = "Packet inspector for The Blockheads")
public class BHInterceptor implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(BHInterceptor.class);
  private static final int CHANNELS = 32; // Seems to be what is used by blockheads
  private static final AtomicBoolean initializedNatives = new AtomicBoolean();
  private static final AtomicInteger packetSession = new AtomicInteger();
  @Getter
  private final IntObjectMap<InterceptedPacket> packets = new IntObjectHashMap<>();
  @Option(names = {"--proxy-port", "-P"}, description = "The port that should be used by the proxy", defaultValue = "15152")
  private short proxyPort;
  @Option(names = {"--proxy-host", "-PH"}, description = "The host the proxy will listen to", defaultValue = "0.0.0.0")
  private String proxyHost;
  @Option(names = {"--server-port", "-S"}, description = "The server port", defaultValue = "15151")
  private short serverPort;
  @Option(names = {"--server-host", "-SH"}, description = "The server host", defaultValue = "127.0.0.1")
  private String serverHost;
  @Option(names = "-D", defaultValue = "false", hidden = true) // Used to export packet data for the GUI frontend
  private boolean forwardData;

  private static MemorySegment copyPackage(MemorySegment packet) {
    // Note, Enet already clones the input data!
    return ENet.enet_packet_create(ENetPacket.data$get(packet), ENetPacket.dataLength$get(packet), ENetPacket.flags$get(packet));
  }

  private static void loadLibraries() {
    if (!initializedNatives.getAndSet(true)) {
      try {
        NativeHelper.loadLibrary("libenet");
      } catch (Throwable e) {
        initializedNatives.set(false);
        throw e;
      }
    }
  }

  public static void main(String... args) {
    System.exit(new CommandLine(new BHInterceptor()).execute(args));
  }

  @Override
  public void run() {
    logger.info("Initializing proxy...");
    // C style error handling is a pain...
    int success;

    // Init native stuff
    loadLibraries();

    if ((success = ENet.enet_initialize()) != 0) {
      throw new IllegalStateException(STR."Unable to initialize ENet! Error code:\{success}");
    }

    // We don't need any fancy memory sharing here, create a confined allocator
    try (Arena allocator = Arena.ofConfined()) {
      MemorySegment realServer = null;
      MemorySegment proxyServer = null;

      try {
        var proxyAddress = ENetAddress.allocate(allocator);
        ENetAddress.port$set(proxyAddress, proxyPort);
        if (this.proxyHost.equals("0.0.0.0")) {
          ENetAddress.host$set(proxyAddress, ENet.ENET_HOST_ANY());
        } else {
          if ((success = ENet.enet_address_set_host(proxyAddress, allocator.allocateUtf8String(this.proxyHost))) != 0) {
            throw new IllegalStateException(STR."Native code error, unable to set proxy host! Error code:\{success}");
          }
        }

        var serverAddress = ENetAddress.allocate(allocator);
        ENetAddress.port$set(serverAddress, serverPort);
        if ((success = ENet.enet_address_set_host(serverAddress, allocator.allocateUtf8String(this.serverHost))) != 0) {
          throw new IllegalStateException(STR."Native code error, unable to set server host! Error code:\{success}");
        }

        proxyServer = ENet.enet_host_create(proxyAddress, 1, CHANNELS, 0, 0);
        if (proxyServer.address() == ENet.NULL().address()) {
          throw new IllegalStateException("Failed to create proxy server due to native error.");
        }

        MemorySegment realServerPeer = null;
        MemorySegment clientPeer = null;
        var event = ENetEvent.allocate(allocator);

        logger.info("Proxy initialized");
        while (!Thread.interrupted()) {
          // Service the proxy server and forward client connection/packets
          while (ENet.enet_host_service(proxyServer, event, 10) != 0) {
            var type = ENetEvent.type$get(event);
            if (type == ENet.ENET_EVENT_TYPE_CONNECT()) {
              logger.info("Client connected to proxy...");
              if (realServer != null) {
                throw new IllegalStateException("WTF?! Remote server connected? This should not be reachable...");
              }

              realServer = ENet.enet_host_create(ENet.NULL(), 1, CHANNELS, 0, 0);
              if (realServer.address() == ENet.NULL().address()) {
                throw new IllegalStateException("Failed to create server host due to native error.");
              }

              realServerPeer = ENet.enet_host_connect(realServer, serverAddress, 2, 0);
              if (realServerPeer.address() == ENet.NULL().address()) {
                throw new IllegalStateException("Failed to connect to server due to native error.");
              }

              clientPeer = ENetEvent.peer$get(event);
            } else if (type == ENet.ENET_EVENT_TYPE_DISCONNECT()) {
              logger.info("Client disconnected from proxy... Killing server connection!");
              clientPeer = null;
              if (realServer != null) {
                ENet.enet_host_destroy(realServer);
                realServer = null;
                realServerPeer = null;
              }

            } else if (type == ENet.ENET_EVENT_TYPE_RECEIVE()) {
              // Note: Enet handles the destruction of sent packets!
              var packet = ENetEvent.packet$get(event);
              attemptPacketDetection(packet, ENetEvent.channelID$get(event), Side.CLIENT);
              if (realServer != null) {
                ENet.enet_peer_send(realServerPeer, ENetEvent.channelID$get(event), copyPackage(packet));
              }
              ENet.enet_packet_destroy(packet);
            }
          }

          // Service the real server connection and forward packets to the client
          while ((realServer != null) && (ENet.enet_host_service(realServer, event, 10) != 0)) {
            var type = ENetEvent.type$get(event);
            if (type == ENet.ENET_EVENT_TYPE_CONNECT()) {
              logger.info("Server connected to proxy!");
            } else if (type == ENet.ENET_EVENT_TYPE_DISCONNECT()) {
              logger.info("Server disconnected from proxy... Killing client connection!");
              ENet.enet_host_destroy(realServer);
              realServer = null;
              realServerPeer = null;
              ENet.enet_peer_reset(clientPeer);
            } else if (type == ENet.ENET_EVENT_TYPE_RECEIVE()) {
              // Note: Enet handles the destruction of sent packets!
              var packet = ENetEvent.packet$get(event);
              ENet.enet_peer_send(clientPeer, ENetEvent.channelID$get(event), copyPackage(packet));
              attemptPacketDetection(packet, ENetEvent.channelID$get(event), Side.SERVER);
              ENet.enet_packet_destroy(packet);
            }
          }
        }
        Thread.currentThread().interrupt(); // Restore interrupt status
      } finally {
        if (proxyServer != null && proxyServer.address() != ENet.NULL().address()) {
          ENet.enet_host_destroy(proxyServer);
        }
        if (realServer != null && realServer.address() != ENet.NULL().address()) {
          ENet.enet_host_destroy(realServer);
        }
        logger.info("Proxy interrupted, shutting down...");
        ENet.enet_deinitialize();
      }
    }
  }

  private void attemptPacketDetection(MemorySegment packet, int channel, Side direction) {
    // We create a slice of the memory, so java is aware of the actual bounds for the direct byte buffer
    var data = ENetPacket.data$get(packet).asSlice(0, (int) ENetPacket.dataLength$get(packet)).asByteBuffer();
    Packet detectedPacket = PacketRegistry.parsePacket(data, direction);
    data.rewind();
    var id = data.get();

    var rawData = new byte[data.limit()];
    data.rewind();
    data.get(rawData);

    if (this.forwardData) {
      var flags = ENetPacket.flags$get(packet);
      var session = packetSession.incrementAndGet();

      packets.put(session, new InterceptedPacket(id, rawData, detectedPacket, direction,
        PacketRegistry.getPacketClass(id, direction), flags, channel));
      MDC.put("packetSession", String.valueOf(session));
    }

    if (detectedPacket != null) {
      logger.info("{} [{}] {}", direction.getPacketFlow(), HexFormat.of().formatHex(new byte[]{id}), detectedPacket);
    } else {
      // No packet detected, dump its raw data
      var content = "<EMPTY>";
      if (data.remaining() > 0) {
        var unknownContent = new byte[data.remaining() - 1];
        data.get(unknownContent);
        content = HexFormat.of().formatHex(unknownContent);
      }
      logger.info("{} [{}] {}", direction.getPacketFlow(), HexFormat.of().formatHex(new byte[]{id}), content);
    }

    if (this.forwardData) {
      MDC.remove("packetSession");
    }
  }
}
