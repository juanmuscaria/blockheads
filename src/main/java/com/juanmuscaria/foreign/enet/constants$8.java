// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_SHORT;
final class constants$8 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$8() {}

  static final VarHandle const$0 = constants$7.const$5.varHandle(MemoryLayout.PathElement.groupElement("dataLength"));
  static final StructLayout const$1 = MemoryLayout.structLayout(
    MemoryLayout.structLayout(
      JAVA_BYTE.withName("command"),
      JAVA_BYTE.withName("channelID"),
      JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
    ).withName("header"),
    JAVA_SHORT.withByteAlignment(1).withName("unreliableSequenceNumber"),
    JAVA_SHORT.withByteAlignment(1).withName("dataLength")
  ).withName("_ENetProtocolSendUnreliable");
  static final VarHandle const$2 = constants$8.const$1.varHandle(MemoryLayout.PathElement.groupElement("unreliableSequenceNumber"));
  static final VarHandle const$3 = constants$8.const$1.varHandle(MemoryLayout.PathElement.groupElement("dataLength"));
    static final StructLayout const$4 = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
      JAVA_SHORT.withByteAlignment(1).withName("unsequencedGroup"),
      JAVA_SHORT.withByteAlignment(1).withName("dataLength")
    ).withName("_ENetProtocolSendUnsequenced");
  static final VarHandle const$5 = constants$8.const$4.varHandle(MemoryLayout.PathElement.groupElement("unsequencedGroup"));
}


