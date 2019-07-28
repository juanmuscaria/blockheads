package io.github.juanmuscaria.blockheads.network.packets;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import io.github.juanmuscaria.blockheads.jna.enet.Enet;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetPacket;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetPeer;
import io.github.juanmuscaria.blockheads.jna.types.Size_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint32_t;
import io.github.juanmuscaria.blockheads.jna.types.Uint8_t;
import io.github.juanmuscaria.blockheads.network.ENetServer;
import io.github.juanmuscaria.blockheads.utils.Utils;

import java.nio.ByteBuffer;

public abstract class BasePacket {
    protected volatile byte[] data;
    protected String name = "BasePacket";

    /**
     * Cria um pacote do ENet com os dados desse pacote.
     * Aviso: Não crie caso não for usar, isso poderá causar um memory leak.
     * @return uma instancia do ENetPacket com os dados dessa classe.
     */
    public synchronized ENetPacket toENetPacket(){
        ByteBuffer buffer = ByteBuffer.allocate(getPacketID().length+getData().length);
        buffer.put(getPacketID()).put(data);
        Pointer name = new Memory(this.name.length()+1);
        name.setString(0,this.name);
        ENetPacket packet = Enet.INSTANCE.enet_packet_create(name,new Size_t(buffer.array().length+1), new Uint32_t(Enet.ENetPacketFlag.ENET_PACKET_FLAG_RELIABLE));

        packet.data.write(0,buffer.array(),0,buffer.array().length);
        ENetServer.logger.trace("Criado pacote:"+packet.data.getString(0));
        return packet;
    }

    /**
     * Pega os dados que estão sendo segurados por esse pacote.
     * @return Uma byte array com os dados desse pacote.
     */
    public synchronized byte[] getData() {
        return data;
    }

    /**
     * Define novos dados para esse pacote.
     * @param data Uma byte array com os novos dados.
     */
    public synchronized void setData(byte[] data) {
        this.data = data;
    }

    /**
     * Pega o nome do pacote.
     * Nota:Não deverá ser usado para definir que tipo de pacote é, use instanceof para diferenciar pacotes.
     * @return O nome do pacote.
     */
    public String getName(){
        return name;
    }

    /**
     * Manda o pacote para um peer (cliente).
     * Nota: Não tenho certeza se é thread-safe.
     * @param peer Instancia do peer do cliente.
     */
    public void sendTo(ENetPeer peer){
        Enet.INSTANCE.enet_peer_send(peer,new Uint8_t(0),toENetPacket());
    }

    public short getChannel(){
        return (short)0;
    }
    public byte[]getPacketID(){
        return Utils.hexStringToByteArray("0000");
    }

}
