package io.github.juanmuscaria.blockheads.network.packets.server;

import io.github.juanmuscaria.blockheads.network.packets.BasePacket;
import io.github.juanmuscaria.blockheads.utils.Utils;

public class PacketConnectionAccepted extends BasePacket {
    public PacketConnectionAccepted(){
        this.name = "PacketConnectionAccepted"; //Define o nome do pacote
        this.data = Utils.hexStringToByteArray("C28B");
    }

    @Override
    public byte[] getPacketID() {
        return Utils.hexStringToByteArray("011F");
    }
}
