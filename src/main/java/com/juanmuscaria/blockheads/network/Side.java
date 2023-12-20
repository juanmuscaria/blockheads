package com.juanmuscaria.blockheads.network;

import lombok.Getter;

/**
 * Side of the connection that sent the packet
 */
@Getter
public enum Side {
  CLIENT("CLIENT > SERVER"),
  SERVER("SERVER > CLIENT");

  /**
   * Human-readable representation of the packet flow
   */
  private final String packetFlow;

  Side(String packetFlow) {
    this.packetFlow = packetFlow;
  }
}
