package com.juanmuscaria.blockheads.network.packets.server;

import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.util.HexFormat;

@ToString
public class KeepAliveResponse implements Packet {
  public static final byte ID = 0x17;
  String data; // Not sure what the data is,
  // maybe something the client needs to be in constant sync with how frequent those packet pairs appear

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {
    // Packets always empty
  }

  @Override
  public void decode(ByteBuffer buffer) {
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    data = HexFormat.of().formatHex(remaining);
  }
}
