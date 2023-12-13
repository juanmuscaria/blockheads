package com.juanmuscaria.blockheads.network.packets.client;

import com.juanmuscaria.blockheads.network.packets.Packet;

import java.nio.ByteBuffer;
import java.util.HexFormat;

public class RequestWorldFragment implements Packet {
  public static final byte ID = 0x03;
  int x; // used to tell which part of the world it is
  int y; // used to tell which part of the world it is
  byte[] unknownData; // Unknown remaining data

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {
    //TODO: Encode
  }

  @Override
  public void decode(ByteBuffer buffer) {
    x = Byte.toUnsignedInt(buffer.get());
    y = Byte.toUnsignedInt(buffer.get()) - 20; // not sure why I need that minus 20, but works I guess
    unknownData = new byte[buffer.remaining()];
    buffer.get(unknownData);
  }

  @Override
  public String toString() {
    return "RequestWorldFragment{" +
      "x=" + x +
      ", y=" + y +
      ", unparsed=" + HexFormat.of().formatHex(unknownData) +
      '}';
  }
}
