package com.juanmuscaria.blockheads.network.packets.client;

import com.dd.plist.NSArray;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;


@ToString
public class DynamicObjectCreateClient extends Packet {
  public static byte ID = 0x0a;
  private byte keys;
  private NSArray data;

  @Override
  public void encode(ByteBuffer buffer) {

  }

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
