package com.juanmuscaria.blockheads.network.packets.client;

import com.dd.plist.NSDictionary;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;

// Seems to contain data about blockheads, what they are doing and their state
@ToString
public class UpdatePlayerActionsAndState extends Packet {
  public static final byte ID = 0x20;
  public NSDictionary data;

  @Override
  public void encode(ByteBuffer buffer) {

  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var data = new ByteArrayOutputStream();
    new GZIPInputStream(new ByteArrayInputStream(remaining)).transferTo(data);
    this.data = BHHelper.parseProperty(data.toByteArray());
  }
}
