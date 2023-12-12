package com.juanmuscaria.blockheads.old;

import com.juanmuscaria.blockheads.old.jna.enet.Enet;
import com.juanmuscaria.blockheads.old.jna.enet.structures.ENetAddress;
import com.juanmuscaria.blockheads.old.jna.enet.structures.ENetEvent;
import com.juanmuscaria.blockheads.old.jna.enet.structures.ENetHost;
import com.juanmuscaria.blockheads.old.jna.enet.structures.ENetPacket;
import com.juanmuscaria.blockheads.old.jna.types.Size_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint16_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint32_t;

import java.lang.ref.Cleaner;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;


public class ENetServer {
  private static final Cleaner cleaner = Cleaner.create();
  private static final int CHANNELS = 0xFFFF;
  private static final int INCOMING_BANDWIDTH = 0;
  private static final int OUTGOING_BANDWIDTH = 0;
  private static final int MAX_CONNECTIONS = 16;

  static {
    Enet.INSTANCE.enet_initialize();
  }

  private final Cleaner.Cleanable cleanable;
  private final AtomicBoolean destroyed = new AtomicBoolean(false);
  private final ENetHost eNetHost;
  private final ENetEvent eNetEvent = new ENetEvent();

  public ENetServer(String ip, int port) {
    ENetAddress eNetAddress = new ENetAddress();
    eNetAddress.port = new Uint16_t(port);
    if (ip == null || ip.isEmpty()) eNetAddress.host = new Uint32_t(Enet.ENET_HOST_ANY);
    else Enet.INSTANCE.enet_address_set_host_ip(eNetAddress, ip);
    this.eNetHost = Enet.INSTANCE.enet_host_create(eNetAddress, new Size_t(MAX_CONNECTIONS), new Size_t(CHANNELS), new Uint32_t(INCOMING_BANDWIDTH), new Uint32_t(OUTGOING_BANDWIDTH)); //Cria a host com
    var cDestroyed = destroyed;
    var cENetHost = eNetHost;
    this.cleanable = cleaner.register(this, () -> {
      if (!cDestroyed.getAndSet(true)) {
        Enet.INSTANCE.enet_host_flush(cENetHost);
        Enet.INSTANCE.enet_host_destroy(cENetHost);
      }
    });
  }

  public synchronized void processEvents() {
//        while (Enet.INSTANCE.enet_host_service(eNetHost,eNetEvent,new Uint32_t(1000)) > 0){
//            if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_CONNECT){
//                System.out.println("Cliente conectado");
//                System.out.println(eNetEvent.peer.connectID);
//                System.out.println(eNetEvent.channelID);
//                Handshake packet = new Handshake();
//                Enet.INSTANCE.enet_peer_send(eNetEvent.peer, new Uint8_t(0),packet.createEnetPacket());
//            }
//            else if (eNetEvent.type== Enet.ENetEventType.ENET_EVENT_TYPE_RECEIVE){
//                System.out.println(generatePacketDump(eNetEvent));
//                BHPayload packet = new BHPayload((short) 0x011F, Utils.hexStringToByteArray("C28B")) {};
//                Enet.INSTANCE.enet_peer_send(eNetEvent.peer, new Uint8_t(0),packet.createEnetPacket());
//                Enet.INSTANCE.enet_packet_destroy(eNetEvent.packet);
//            }
//            else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_DISCONNECT){
//                System.out.println("Cliente desconectado");
//            }
//        }
  }

  /**
   * Deve ser chamado antes de inutilizar esse objeto. AVISO:Não deixe o GC limpar esse objeto sem chamar esse metodo antes!
   */
  public void destroy() {
    if (!destroyed.getAndSet(true)) {
      Enet.INSTANCE.enet_host_flush(eNetHost);
      Enet.INSTANCE.enet_host_destroy(eNetHost);
    }
  }

  private String generatePacketDump(ENetEvent event) {
    StringBuilder dump = new StringBuilder();
    ByteBuffer buffer = ByteBuffer.allocate(30);
    Enet.INSTANCE.enet_address_get_host_ip(event.peer.address, buffer, new Size_t(30));
    ENetPacket packet = event.packet;
    dump.append("Dump do pacote recebido de: ").append(new String(buffer.array())).append(":").append(event.peer.address.port.intValue()).append("\n");
    dump.append("Tamanho do pacote: ").append(packet.dataLength.intValue()).append("\n");
    dump.append("Conteudo do pacote: ").append(packet.data.getString(0)).append("\n");
    dump.append("Flags do pacote: ").append(packet.flags).append("\n");
    dump.append("Canal que foi recebido o pacote: ").append(event.channelID).append("\n");
    return dump.toString();
  }
}
