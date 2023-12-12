package com.juanmuscaria.blockheads.intercept;

import com.juanmuscaria.blockheads.NativeHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import com.juanmuscaria.blockheads.network.packets.PacketRegistry;
import com.juanmuscaria.foreign.enet.ENetAddress;
import com.juanmuscaria.foreign.enet.ENetEvent;
import com.juanmuscaria.foreign.enet.ENetPacket;
import com.juanmuscaria.foreign.enet.enet_h;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.HexFormat;


/**
 * Proxy blockheads connection and inspect what is going on under the hood
 */
public class BHInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(BHInterceptor.class);
  private static final int CHANNELS = 32; // Seems to be what is used by blockheads
  private static final short PROXY_PORT = 15152;
  private static final short SERVER_PORT = 15151;
  private static final String SERVER_HOST = "127.0.0.1";

  public static void main(String... args) {
    // Init native stuff
    NativeHelper.loadLibrary("libenet");
    enet_h.enet_initialize();

    // We don't need any fancy memory sharing here, create a confined allocator
    try (Arena allocator = Arena.ofConfined()) {
      var proxyHost = ENetAddress.allocate(allocator);
      ENetAddress.port$set(proxyHost, PROXY_PORT);
      ENetAddress.host$set(proxyHost, enet_h.ENET_HOST_ANY());

      var serverHost = ENetAddress.allocate(allocator);
      ENetAddress.port$set(serverHost, SERVER_PORT);
      enet_h.enet_address_set_host(serverHost, allocator.allocateUtf8String(SERVER_HOST));

      var proxyServer = enet_h.enet_host_create(proxyHost, 1, CHANNELS, 0, 0);
      MemorySegment realServer = null;
      MemorySegment realServerPeer = null;
      MemorySegment clientPeer = null;
      logger.info("Proxy initialized");

      var event = ENetEvent.allocate(allocator);
      //noinspection InfiniteLoopStatement
      while (true) {
        // Service the proxy server and forward client connection/packets
        while (enet_h.enet_host_service(proxyServer, event, 10) != 0) {
          var type = ENetEvent.type$get(event);
          if (type == enet_h.ENET_EVENT_TYPE_CONNECT()) {
            logger.info("Client connected to proxy...");
            if (realServer != null) {
              throw new IllegalStateException("Inconsistent state! Remote server connected?");
            }
            realServer = enet_h.enet_host_create(enet_h.NULL(), 1, CHANNELS, 0, 0);
            realServerPeer = enet_h.enet_host_connect(realServer, serverHost, 2, 0);
            clientPeer = ENetEvent.peer$get(event);
          } else if (type == enet_h.ENET_EVENT_TYPE_DISCONNECT()) {
            logger.info("Client disconnected from proxy... Killing server connection!");
            clientPeer = null;
            if (realServer != null) {
              enet_h.enet_host_destroy(realServer);
              realServer = null;
              realServerPeer = null;
            }

          } else if (type == enet_h.ENET_EVENT_TYPE_RECEIVE()) {
            // Note: Enet handles the destruction of sent packets!
            var packet = ENetEvent.packet$get(event);
            attemptPacketDetection(packet, ENetEvent.channelID$get(event), Direction.CLIENT_TO_SERVER);
            if (realServer != null) {
              enet_h.enet_peer_send(realServerPeer, ENetEvent.channelID$get(event), copyPackage(packet));
            }
            enet_h.enet_packet_destroy(packet);
          }
          break;
        }

        // Service the real server connection and forward packets to the client
        while ((realServer != null) && (enet_h.enet_host_service(realServer, event, 10) != 0)) {
          var type = ENetEvent.type$get(event);
          if (type == enet_h.ENET_EVENT_TYPE_CONNECT()) {
            logger.info("Server connected to proxy!");
          } else if (type == enet_h.ENET_EVENT_TYPE_DISCONNECT()) {
            logger.info("Server disconnected from proxy... Killing client connection!");
            enet_h.enet_host_destroy(realServer);
            realServer = null;
            realServerPeer = null;
            enet_h.enet_peer_reset(clientPeer);
          } else if (type == enet_h.ENET_EVENT_TYPE_RECEIVE()) {
            // Note: Enet handles the destruction of sent packets!
            var packet = ENetEvent.packet$get(event);
            enet_h.enet_peer_send(clientPeer, ENetEvent.channelID$get(event), copyPackage(packet));
            attemptPacketDetection(packet, ENetEvent.channelID$get(event), Direction.SERVER_TO_CLIENT);
            enet_h.enet_packet_destroy(packet);
          }
          break;
        }
      }
    } finally {
      enet_h.enet_deinitialize();
    }
  }


  private static MemorySegment copyPackage(MemorySegment packet) {
    // Note, Enet already clones the input data!
    return enet_h.enet_packet_create(ENetPacket.data$get(packet), ENetPacket.dataLength$get(packet), ENetPacket.flags$get(packet));
  }

  private static void attemptPacketDetection(MemorySegment packet, int channel, Direction direction) {
    // We create a slice of the memory, so java is aware of the actual bounds for the direct byte buffer
    var data = ENetPacket.data$get(packet).asSlice(0, (int) ENetPacket.dataLength$get(packet)).asByteBuffer();
    Packet detectedPacket;
    if (direction == Direction.CLIENT_TO_SERVER) {
      detectedPacket = PacketRegistry.parseClientPacket(data);
    } else {
      detectedPacket = PacketRegistry.parseServerPacket(data);
    }

    data.rewind();
    var id = data.get();

    if (detectedPacket != null) {
      logger.debug("{} [{}] {}", direction.text, HexFormat.of().formatHex(new byte[]{id}), detectedPacket);
    } else {
      // No packet detected, dump its raw data
      var content = "<EMPTY>";
      if (data.remaining() > 0) {
        var unknownContent = new byte[data.remaining() - 1];
        data.get(unknownContent);
        content = HexFormat.of().formatHex(unknownContent);
      }
      logger.debug("{} [{}] {}", direction.text, HexFormat.of().formatHex(new byte[]{id}), content);
    }
  }

  enum Direction {
    CLIENT_TO_SERVER("CLIENT > SERVER"),
    SERVER_TO_CLIENT("SERVER > CLIENT");

    private final String text;

    Direction(String text) {
      this.text = text;
    }
  }
}
