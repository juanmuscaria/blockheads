import com.dd.plist.NSObject;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import io.github.juanmuscaria.blockheads.jna.enet.Enet;
import io.github.juanmuscaria.blockheads.jna.enet.structures.*;
import io.github.juanmuscaria.blockheads.jna.types.Size_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint16_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint32_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint8_t;
import io.github.juanmuscaria.blockheads.network.packets.DummyPacket;
import io.github.juanmuscaria.blockheads.utils.Reflection;
import io.github.juanmuscaria.blockheads.utils.Utils;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;

public class Teste {
    @Test
    public void connectToServer() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        ENetAddress address = new ENetAddress();
        System.out.println("Iniciando cliente");
        address.port = new Uint16_t(15151);
        ENetHost client = Enet.INSTANCE.enet_host_create(null,new Size_t(1),new Size_t(2),new Uint32_t(0),new Uint32_t(0));
        ENetEvent event =  new ENetEvent();
        Enet.INSTANCE.enet_address_set_host(address,"127.0.0.1");
        ENetPeer peer = Enet.INSTANCE.enet_host_connect(client,address,new Size_t(2),new Uint32_t(0));
        boolean a = false;
        if (peer == null)throw new RuntimeException("Erro ao criar a conexão");
        while (true){
            while (Enet.INSTANCE.enet_host_service(client,event,new Uint32_t(1000)) > 0){
                if (event.type == Enet.ENetEventType.ENET_EVENT_TYPE_CONNECT){
                    System.out.println("Cliente conectado");


                }
                else if (event.type == Enet.ENetEventType.ENET_EVENT_TYPE_RECEIVE){
                    System.out.println(genetatePacketDump(event));
                    Enet.INSTANCE.enet_packet_destroy(event.packet);
                    if (a)continue;
                    String data1 = new String(Utils.hexStringToByteArray("3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d38223f3e0a3c21444f435459504520706c697374205055424c494320222d2f2f4170706c652f2f44544420504c49535420312e302f2f454e222022687474703a2f2f7777772e6170706c652e636f6d2f445444732f50726f70657274794c6973742d312e302e647464223e0a3c706c6973742076657273696f6e3d22312e30223e0a3c646963743e0a20202020202020203c6b65793e616c6961733c2f6b65793e0a20202020202020203c737472696e673e4a55414e4d555343415249413c2f737472696e673e0a20202020202020203c6b65793e69436c6f756449443c2f6b65793e0a20202020202020203c737472696e673e66643338383737383432663863366633626239626265653430393833356637653c2f737472696e673e0a20202020202020203c6b65793e6c6f63616c3c2f6b65793e0a20202020202020203c747275652f3e0a20202020202020203c6b65793e6d69634f72537065616b65724f6e3c2f6b65793e0a20202020202020203c747275652f3e0a20202020202020203c6b65793e6d696e6f7256657273696f6e3c2f6b65793e0a20202020202020203c696e74656765723e333c2f696e74656765723e0a20202020202020203c6b65793e706c6179657249443c2f6b65793e0a20202020202020203c737472696e673e34613565633961346365383665333430333363666431626236626130396630333c2f737472696e673e0a20202020202020203c6b65793e756469644e65773c2f6b65793e0a20202020202020203c737472696e673e30316234363136653432393337396630323435346137356432383632336235373c2f737472696e673e0a20202020202020203c6b65793e766f696365436f6e6e65637465643c2f6b65793e0a20202020202020203c66616c73652f3e0a3c2f646963743e0a3c2f706c6973743e0a"));
                    NSObject data = PropertyListParser.parse(data1.getBytes());
                    DummyPacket packet = new DummyPacket(data.toXMLPropertyList().getBytes(),"1f");
                    Enet.INSTANCE.enet_peer_send(peer,new Uint8_t(0),packet.toENetPacket());
                    a =true;
                }
                else if (event.type == Enet.ENetEventType.ENET_EVENT_TYPE_DISCONNECT){
                    System.out.println("Cliente desconectado");
                    break;
                }
                else if (event.type == Enet.ENetEventType.ENET_EVENT_TYPE_NONE){
                    System.out.println("NONE");
                }
            }
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
        dump.append("Conteudo do pacote em hex: ").append(Utils.bytesToHex(packet.data.getByteArray(0,packet.dataLength.intValue()))).append("\n");
        dump.append("Flags do pacote: ").append(packet.flags).append("\n");
        dump.append("Canal que foi recebido o pacote: ").append(event.channelID).append("\n");
        return dump.toString();
    }
}
