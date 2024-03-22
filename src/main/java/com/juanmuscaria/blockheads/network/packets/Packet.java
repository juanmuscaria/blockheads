package com.juanmuscaria.blockheads.network.packets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.HexFormat;

public abstract class Packet {
  protected static final Logger logger = LoggerFactory.getLogger(Packet.class);
  private String description;


  public void encode(ByteBuffer buffer) {
    // Packets always empty
  }

  public void decode(ByteBuffer buffer) throws Exception {
    if (buffer.remaining() > 0 && logger.isDebugEnabled()) {
      logger.debug("{} Actually had content!", this.getClass());
      byte[] remaining = new byte[buffer.remaining()];
      buffer.get(remaining);
      logger.debug("Packet content: {}", HexFormat.of().formatHex(remaining));
    }
  }

  public final String describePacket() {
    if (description == null) {
      description = this.describePacket();
    }
    return description;
  }

  protected String genDescription() {
    return this.toString();
  }

  public String displayName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
