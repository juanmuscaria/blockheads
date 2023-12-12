package com.juanmuscaria.blockheads.old;

import com.juanmuscaria.blockheads.network.packets.PacketRegistry;
import com.juanmuscaria.blockheads.old.jna.enet.Enet;
import com.juanmuscaria.blockheads.old.jna.enet.structures.*;
import com.juanmuscaria.blockheads.old.jna.types.Size_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint16_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint32_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint8_t;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

public class BHProxy implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(BHProxy.class);

  private static final int CHANNELS = 0xff;
  private static final int INCOMING_BANDWIDTH = 0;
  private static final int OUTGOING_BANDWIDTH = 0;
  private static final int MAX_CONNECTIONS = 1;

  private static final int PROXY_PORT = 15151;

  private static final int SERVER_PORT = 15150;
  private static final String SERVER_HOST = "127.0.0.1";

  static {
    Enet.INSTANCE.enet_initialize();
  }

  private final ENetEvent eNetEvent = new ENetEvent();
  public boolean dumpClient = true;
  public boolean dumpServer = true;
  ENetHost server;
  ENetHost client;
  ENetPeer serverPeer;
  ENetPeer clientPeer;

  public BHProxy() {
    ENetAddress eNetAddress = new ENetAddress();
    eNetAddress.port = new Uint16_t(PROXY_PORT);
    eNetAddress.host = new Uint32_t(Enet.ENET_HOST_ANY);
    server = Enet.INSTANCE.enet_host_create(eNetAddress, new Size_t(MAX_CONNECTIONS), new Size_t(CHANNELS), new Uint32_t(INCOMING_BANDWIDTH), new Uint32_t(OUTGOING_BANDWIDTH));
  }

  public static void main(String... args) throws InterruptedException {
    var proxy = new BHProxy();
    var proxyThread = new Thread(proxy, "PROXY");
    proxyThread.setDaemon(false);
    proxyThread.start();
    proxyThread.join();
  }

  @Override
  public void run() {
    logger.info("Proxy initialized");
    while (true) {
      while (Enet.INSTANCE.enet_host_service(server, eNetEvent, new Uint32_t(100)) != 0) {
        if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_CONNECT) {
          logger.info("Client connected to proxy");
          ENetAddress eNetAddress = new ENetAddress();
          eNetAddress.port = new Uint16_t(SERVER_PORT);
          System.out.println(Enet.INSTANCE.enet_address_set_host_ip(eNetAddress, SERVER_HOST));
          client = Enet.INSTANCE.enet_host_create(null, new Size_t(1), new Size_t(2), new Uint32_t(0), new Uint32_t(0));
          serverPeer = Enet.INSTANCE.enet_host_connect(client, eNetAddress, new Size_t(2), new Uint32_t(0));
          clientPeer = eNetEvent.peer;
        } else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_RECEIVE) {
          if (dumpClient) {
            var bytes = eNetEvent.packet.data.getByteArray(0, eNetEvent.packet.dataLength.intValue());
            var packet = PacketRegistry.parseClientPacket(ByteBuffer.wrap(bytes));
            if (packet != null) {
              logger.debug("Client > Server {}", packet);
            } else {
              String id = Utils.bytesToHex(new byte[]{bytes[0]});
              String data = "NONE";
              if (eNetEvent.packet.dataLength.intValue() > 1) {
                data = Utils.bytesToHex(eNetEvent.packet.data.getByteArray(1, eNetEvent.packet.dataLength.intValue() - 1));
              }
              logger.debug("Client > Server [{}] {}", id, data);
            }
          }
          Enet.INSTANCE.enet_peer_send(client.peers, new Uint8_t(eNetEvent.channelID), copyPacket(eNetEvent.packet));
          Enet.INSTANCE.enet_packet_destroy(eNetEvent.packet);
          Enet.INSTANCE.enet_host_flush(client);
        } else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_DISCONNECT) {
          logger.info("Client disconnected from proxy");
          //Enet.INSTANCE.enet_host_destroy(client);
          clientPeer = null;
          client = null;
        }
      }
      if (client != null) {
        while (Enet.INSTANCE.enet_host_service(client, eNetEvent, new Uint32_t(100)) != 0) {
          if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_CONNECT) {
            logger.info("Proxied server connected");
          } else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_RECEIVE) {
            if (dumpServer) {
              var bytes = eNetEvent.packet.data.getByteArray(0, eNetEvent.packet.dataLength.intValue());
              var packet = PacketRegistry.parseServerPacket(ByteBuffer.wrap(bytes));
              if (packet != null) {
                logger.debug("Server > Client {}", packet);
              } else {
                String id = Utils.bytesToHex(new byte[]{bytes[0]});
                String data = "NONE";
                if (eNetEvent.packet.dataLength.intValue() > 1) {
                  data = Utils.bytesToHex(eNetEvent.packet.data.getByteArray(1, eNetEvent.packet.dataLength.intValue() - 1));
                }
                logger.debug("Server > Client [{}] {}", id, data);
              }
            }
            Enet.INSTANCE.enet_peer_send(server.peers, new Uint8_t(eNetEvent.channelID), copyPacket(eNetEvent.packet));
            Enet.INSTANCE.enet_packet_destroy(eNetEvent.packet);
            Enet.INSTANCE.enet_host_flush(server);
          } else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_DISCONNECT) {
            logger.info("Proxied server disconnected");
            //Enet.INSTANCE.enet_host_destroy(client);
            clientPeer = null;
            client = null;
          }
        }
      }
    }
  }

  public void sendRawPacket(String rawData, ENetPeer peer) {
    System.out.println("Sending injected data> " + rawData);
    byte[] bytes = Utils.hexStringToByteArray(rawData);
    Pointer data = new Memory(bytes.length + 1);
    ENetPacket packet = Enet.INSTANCE.enet_packet_create(data, new Size_t(bytes.length + 1), new Uint32_t(Enet.ENetPacketFlag.ENET_PACKET_FLAG_RELIABLE));
    packet.data.write(0, bytes, 0, bytes.length);
    Enet.INSTANCE.enet_peer_send(peer, new Uint8_t(0), packet);
  }

  public ENetPacket copyPacket(ENetPacket original) {
    byte[] bytes = original.data.getByteArray(0, original.dataLength.intValue());
    Pointer data = new Memory(bytes.length);
    ENetPacket packet = Enet.INSTANCE.enet_packet_create(data, new Size_t(bytes.length), new Uint32_t(1));
    packet.data.write(0, bytes, 0, bytes.length);
    return packet;
  }
}
