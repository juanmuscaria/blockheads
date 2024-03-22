package com.juanmuscaria.blockheads.network.packets.server;

import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.util.HexFormat;

// Not exactly sure, seems to remove dynamic objects from the world?
@ToString
public class DynamicObjectRemove extends Packet {
  public static byte ID = 0x09;
  private byte keys;
  private String data;

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    keys = buffer.get();
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    data = HexFormat.of().formatHex(remaining);
  }
}
