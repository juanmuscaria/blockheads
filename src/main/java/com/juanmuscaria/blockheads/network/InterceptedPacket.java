package com.juanmuscaria.blockheads.network;

import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Holds information about a packet that has been intercepted
 */
@Getter
@AllArgsConstructor
public class InterceptedPacket {
  byte id;
  byte[] rawData;
  Packet parsedPacket;
  Side direction;
  Class<? extends Packet> mapping;
  int flags;
  int channel;
}
