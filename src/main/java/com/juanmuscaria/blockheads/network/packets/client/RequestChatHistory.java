package com.juanmuscaria.blockheads.network.packets.client;

import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;

@ToString
public class RequestChatHistory implements Packet {
  public static final byte ID = 0x05;

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
    if (buffer.remaining() > 0) {
      logger.warn("{} Actually had content!", this.getClass());
    }
  }
}
