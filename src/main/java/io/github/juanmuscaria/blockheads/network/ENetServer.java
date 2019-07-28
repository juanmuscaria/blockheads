package io.github.juanmuscaria.blockheads.network;

import io.github.juanmuscaria.blockheads.jna.enet.Enet;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetAddress;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetEvent;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetHost;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetPacket;
import io.github.juanmuscaria.blockheads.jna.types.Size_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint16_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint32_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint8_t;
import io.github.juanmuscaria.blockheads.network.packets.PacketProcessos;
import io.github.juanmuscaria.blockheads.network.packets.server.PacketHandshake;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;


public class ENetServer {
    private static final int CHANNELS = 100; //Define a quantidade maxima de canais a ser usados.
    private static final int INCOMING_BANDWIDTH = 0;//Define o tamanho do tráfego de entrada, deixe 0 para qualquer tamanho.
    private static final int OUTGOING_BANDWIDTH = 0;//Define o tamanho do tráfego de saida, deixe 0 para qualquer tamanho.
    private static final int MAX_CONNECTIONS = 16;//Define o maximo de peers (jogadores) que podem conectar com o servidor
    public static final Logger logger = LogManager.getLogger("ENetServer");
    private ENetHost eNetHost;
    private ENetEvent eNetEvent = new ENetEvent();
    private PacketProcessos processos = new PacketProcessos();
    private volatile boolean isDestroyed = false;
    static {
        Enet.INSTANCE.enet_initialize();
        Runtime.getRuntime().addShutdownHook(new Unload());
    }
    /**
     * Classe que irá gerencias todas as coisas do ENet.
     * @param ip O ip que será usado pelo enet, pode ser null ou em branco para aceitar qualquer host.
     * @param port Porta que será usada pelo servidor;
     */
    public ENetServer(String ip, int port){
        ENetAddress eNetAddress = new ENetAddress();
        eNetAddress.port = new Uint16_t(port
        );
        if (ip == null || ip.isEmpty()) eNetAddress.host = new Uint32_t(Enet.ENET_HOST_ANY);
        else Enet.INSTANCE.enet_address_set_host_ip(eNetAddress,ip);
        this.eNetHost = Enet.INSTANCE.enet_host_create(eNetAddress,new Size_t(MAX_CONNECTIONS),new Size_t(CHANNELS),new Uint32_t(INCOMING_BANDWIDTH),new Uint32_t(OUTGOING_BANDWIDTH)); //Cria a host com
    }

    /**
     *  Chame esse metodo no game tick para processar eventos relacionados a conexão (como recebimento de pacotes, novo cliente conectado,etc).
     */
    public synchronized void processEvents(){
        while (Enet.INSTANCE.enet_host_service(eNetHost,eNetEvent,new Uint32_t(1000)) > 0){
            if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_CONNECT){
                System.out.println("Cliente conectado");
                System.out.println(eNetEvent.peer.data);
                System.out.println(eNetEvent.channelID);
                PacketHandshake packet = new PacketHandshake();
                Enet.INSTANCE.enet_peer_send(eNetEvent.peer, new Uint8_t(0),packet.toENetPacket());

            }
            else if (eNetEvent.type== Enet.ENetEventType.ENET_EVENT_TYPE_RECEIVE){
                logger.debug(genetatePacketDump(eNetEvent));
                if (!processos.process(eNetEvent.packet,eNetEvent.peer)){
                    logger.debug("Falha ao processar um pacote, gerando dump do pacote e desconectando cliente:");
                    logger.debug(genetatePacketDump(eNetEvent));
                    //Enet.INSTANCE.enet_peer_disconnect(eNetEvent.peer,new Uint32_t(0)); //Isso fará o programa crashar
                }
                Enet.INSTANCE.enet_packet_destroy(eNetEvent.packet);
            }
            else if (eNetEvent.type == Enet.ENetEventType.ENET_EVENT_TYPE_DISCONNECT){
                System.out.println("Cliente desconectado");
            }
        }
    }

    /**
     * Deve ser chamado antes de inutilizar esse objeto. AVISO:Não deixe o GC limpar esse objeto sem chamar esse metodo antes!
     */
    public synchronized void destroy(){
        if (isDestroyed)return;
        Enet.INSTANCE.enet_host_flush(eNetHost);
        Enet.INSTANCE.enet_host_destroy(eNetHost);
        isDestroyed = true;
    }

    public void finalize(){
        if (isDestroyed)return;
        System.err.println("AVISO:Um objeto que não foi destruido está sendo limpo pelo GC, isso poderá causar memory leaks!");
        try {
            Enet.INSTANCE.enet_host_flush(eNetHost);
            Enet.INSTANCE.enet_host_destroy(eNetHost);
            isDestroyed = true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private String genetatePacketDump(ENetEvent event){
        StringBuilder dump = new StringBuilder();
        ByteBuffer buffer = ByteBuffer.allocate(30);
        Enet.INSTANCE.enet_address_get_host_ip(event.peer.address,buffer,new Size_t(30));
        ENetPacket packet = event.packet;
        dump.append("Dump do pacote recebido de: ").append(new String(buffer.array())).append(":").append(event.peer.address.port.intValue()).append("\n");
        dump.append("Tamanho do pacote: ").append(packet.dataLength.intValue()).append("\n");
        dump.append("Conteudo do pacote: ").append(packet.data.getString(0)).append("\n");
        dump.append("Flags do pacote: ").append(packet.flags).append("\n");
        dump.append("Canal que foi recebido o pacote: ").append(event.channelID).append("\n");
        return dump.toString();
    }

    private static class Unload extends Thread{
        @Override
        public void run(){
            Enet.INSTANCE.enet_deinitialize();
        }
    }
}
