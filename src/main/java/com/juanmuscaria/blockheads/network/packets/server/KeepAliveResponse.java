package com.juanmuscaria.blockheads.network.packets.server;

import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;

@ToString
public class KeepAliveResponse extends Packet {
  public static final byte ID = 0x17;
  byte[] unknown; // Not sure what the data is,
  // maybe something the client needs to be in constant sync with how frequent those packet pairs appear
  // Seems to be something with anticheat?

  @Override
  public void encode(ByteBuffer buffer) {

  }

  @Override
  public void decode(ByteBuffer buffer) {
    unknown = new byte[buffer.remaining()];
    buffer.get(unknown);
  }
}
