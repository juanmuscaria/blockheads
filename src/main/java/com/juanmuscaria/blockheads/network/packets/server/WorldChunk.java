package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSData;
import com.dd.plist.NSDictionary;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;

import java.nio.ByteBuffer;

public class WorldChunk extends Packet {
  public static byte ID = 0x04;
  // Maybe I have those flipped, idk
  int x; // used to tell which part of the world it is
  int y; // used to tell which part of the world it is
  short unknownShort; // unknown
  byte[] blocks; // gzipped - world tiles, read from world_db/blocks DBi x_u index
  byte[] lightBlocks; // gzipped - block light data? map data? read from lightBlocks/main DBi <player_uuid>_<x>_<y> index

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    x = Byte.toUnsignedInt(buffer.get());
    y = Byte.toUnsignedInt(buffer.get()) - 20; // not sure why I need that minus 20, but works I guess
    unknownShort = buffer.getShort();
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var dict = BHHelper.<NSDictionary>parseProperty(remaining);
    blocks = ((NSData) dict.get("t")).bytes();
    lightBlocks = ((NSData) dict.get("r")).bytes();
  }
}
