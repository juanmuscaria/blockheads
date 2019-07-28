package io.github.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import io.github.juanmuscaria.blockheads.network.packets.BasePacket;
import io.github.juanmuscaria.blockheads.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketHandshake extends BasePacket {

    /**
     * Cria o pacote de handshake, ele deve ser enviado logo a após a conexão com o servidor.
     */
    public PacketHandshake() {
        this.name = "PacketHandshake"; //Define o nome do pacote

        NSDictionary object = new NSDictionary(); //Cria uma plist
        object.put("worldID","d8248f0f9c1ad3ce72c8e2d20f3ceaad"); //adiciona a chave worldID na lista, ainda não sei como esse id é gerado.
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            PropertyListParser.saveAsBinary(object,data); //Escreve a bplist na output stream.
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.data =  data.toByteArray(); //Define os dados sendo segurados por esse pacote
        this.data = Utils.hexStringToByteArray("232662706C6973743030D1010257776F726C6449445F10206330383731633563636363626136323037646466383661376361333866333366080B130000000000000101000000000000000300000000000000000000000000000036");
    }

    @Override
    public byte[] getPacketID() {
        return Utils.hexStringToByteArray("2326");
    }
}
