package io.github.juanmuscaria.blockheads.network.packets.server;

import io.github.juanmuscaria.blockheads.network.packets.BasePacket;
import io.github.juanmuscaria.blockheads.utils.Utils;

public class PacketKickDubleLogin extends BasePacket {
    public PacketKickDubleLogin(){
        this.name = "PacketHandshake"; //Define o nome do pacote
        this.data = Utils.hexStringToByteArray("1EC388C3B37F");
    }

    @Override
    public short getChannel() {
        return (short)2;
    }

    @Override
    public byte[] getPacketID() {
        return Utils.hexStringToByteArray("2602");
    }
}
