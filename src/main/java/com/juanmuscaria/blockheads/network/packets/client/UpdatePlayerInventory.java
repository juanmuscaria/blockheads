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
public class UpdatePlayerInventory implements Packet {
  public static final byte ID = 0x21;
  int blockheadId;
  NSArray data; // Array of slots? Seems to change as you move around, baskets seems to be a dictionary

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {

  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    this.blockheadId = Byte.toUnsignedInt(buffer.get());
    buffer.position(buffer.position() + 7); // Skip empty data, seems to always be empty?
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var data = new ByteArrayOutputStream();
    new GZIPInputStream(new ByteArrayInputStream(remaining)).transferTo(data);
    this.data = BHHelper.parseProperty(data.toByteArray());
  }
}
