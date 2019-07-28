package io.github.juanmuscaria.blockheads.network.packets;

import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetPacket;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetPeer;
import io.github.juanmuscaria.blockheads.network.ENetServer;
import io.github.juanmuscaria.blockheads.network.packets.client.PacketLogin;
import io.github.juanmuscaria.blockheads.network.packets.server.PacketConnectionAccepted;
import io.github.juanmuscaria.blockheads.utils.Utils;

public class PacketProcessos {
    public PacketProcessos(){

    }
    public boolean process(ENetPacket packet, ENetPeer peer){
                switch (Utils.bytesToHex(new byte[]{packet.data.getByte(0)}).toLowerCase()){
                    case "1f":
                        try {
                            PacketLogin login = new PacketLogin(packet.data.getByteArray(0,packet.dataLength.intValue()));
                            new DummyPacket(Utils.hexStringToByteArray("1f8b08000000000000034b2ac8c92c2e3130b8c7c8c4ccc2cac6cec1c9c5cdc3cbc72f2028242c222a262e2129252d13599e5f94931292999b0a61f925e6a6c60b08149724169504e4179524e604e417eb55c66564a667a416038532f34af42ac2928b5253324ba28a12f352f27383535353a2f3f2831233f340061545178035faa496a5e684152796a57abaa0eaafc4b4a1225e801fec80f0cc94920cdfc4e4a2fca8d48a82d4a212dffc94d498dcccbcfca2b0d4a2e2ccfc3c6587b9390c4ca20c0ca1e1fe413e2e824c428272894a0c402014abb5f28a920babc3730186780185140b23138b348334cb64c3c414e3e45473a3648b54a3142383342027313145909943d0eaa42013038700330307832a833e8325830f43344312432e4325432b430fc36c86750c07184e33dc6078c8f09ce115c35b864f0cdf19fe30fc63546454615467d462d4660003264608cd20cb8002187501f1087c6b87010000"),"01").sendTo(peer);
                            Thread.sleep(1000L);
                            new PacketConnectionAccepted().sendTo(peer);
                            Thread.sleep(1000L);
                            new DummyPacket(Utils.hexStringToByteArray("62706C6973743030C2A0"),"1E").sendTo(peer);
                        }
                        catch (Exception e){
                            ENetServer.logger.trace(e);
                            return false;
                        }
                        break;

                    default:
                        System.out.println(Utils.bytesToHex(new byte[]{packet.data.getByte(0)}).toLowerCase());
                        return false;
                }
        return true;
    }
}
