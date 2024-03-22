package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSArray;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;

@ToString
public class DynamicObjectCreate extends Packet {
  public static byte ID = 0x07;
  private byte keys;
  private NSArray data; // a gzipped plist containing an array of base64 stuff

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    keys = buffer.get();
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var data = new ByteArrayOutputStream();
    new GZIPInputStream(new ByteArrayInputStream(remaining)).transferTo(data);
    this.data = BHHelper.parseProperty(data.toByteArray());
  }
}
