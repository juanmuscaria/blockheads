package io.github.juanmuscaria.blockheads.network.packets.client;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.XMLPropertyListParser;
import io.github.juanmuscaria.blockheads.network.packets.BasePacket;
import io.github.juanmuscaria.blockheads.utils.Utils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class PacketLogin extends BasePacket {
    private NSDictionary loginData;

    public PacketLogin (byte[] data) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        if (data[0] == getPacketID()[0])
            loginData = (NSDictionary) XMLPropertyListParser.parse(Arrays.copyOfRange(data,1,data.length-1));
        else
            loginData = (NSDictionary) XMLPropertyListParser.parse(data);
    }
    @Override
    public byte[] getPacketID() {
        return Utils.hexStringToByteArray("1f");
    }
    public NSDictionary getLoginData(){
        return loginData;
    }

}
