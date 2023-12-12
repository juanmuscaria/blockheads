package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSData;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

//TODO: parse data
@ToString
public class BlockheadsData implements Packet {
  public static byte ID = 0x06;
  byte[] unknownData = new byte[124];
  byte[] foundItems_v2; // foundItems_v2 - gzip, plist
  byte[] foundItems; // foundItems - plist
  Map<String, byte[]> blockheadFiles = new HashMap<>(); // blockheadFiles - values are gzip

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {

  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    buffer.get(unknownData);
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var dict = BHHelper.<NSDictionary>parseProperty(remaining);
    foundItems_v2 = ((NSData) dict.get("foundItems_v2")).bytes();
    foundItems = ((NSData) dict.get("foundItems")).bytes();
    for (Map.Entry<String, NSObject> entry : ((NSDictionary) dict.get("blockheadFiles")).getHashMap().entrySet()) {
      blockheadFiles.put(entry.getKey(), ((NSData) entry.getValue()).bytes());
    }
    //Packet.printDict((NSDictionary) BinaryPropertyListParser.parse(foundItems));
  }
}
