package io.github.juanmuscaria.blockheads.network.packets;

import io.github.juanmuscaria.blockheads.utils.Utils;

public class DummyPacket extends BasePacket {
    private String id = "";
    public DummyPacket (byte[] data,String id){
        this.name = "Dummy";
        this.data = data;
        this.id = id;
    }
    @Override
    public byte[] getPacketID() {
        return Utils.hexStringToByteArray(id);
    }
}
