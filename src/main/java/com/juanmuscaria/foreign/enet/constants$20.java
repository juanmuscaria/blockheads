// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_SHORT;
final class constants$20 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$20() {}

  static final StructLayout const$0 = MemoryLayout.structLayout(
    JAVA_SHORT.withName("outgoingReliableSequenceNumber"),
    JAVA_SHORT.withName("outgoingUnreliableSequenceNumber"),
    JAVA_SHORT.withName("usedReliableWindows"),
    MemoryLayout.sequenceLayout(16, JAVA_SHORT).withName("reliableWindows"),
    JAVA_SHORT.withName("incomingReliableSequenceNumber"),
    JAVA_SHORT.withName("incomingUnreliableSequenceNumber"),
    MemoryLayout.paddingLayout(6),
    MemoryLayout.structLayout(
      MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("next"),
        RuntimeHelper.POINTER.withName("previous")
      ).withName("sentinel")
    ).withName("incomingReliableCommands"),
    MemoryLayout.structLayout(
      MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("next"),
        RuntimeHelper.POINTER.withName("previous")
      ).withName("sentinel")
    ).withName("incomingUnreliableCommands")
  ).withName("_ENetChannel");
  static final VarHandle const$1 = constants$20.const$0.varHandle(MemoryLayout.PathElement.groupElement("outgoingReliableSequenceNumber"));
  static final VarHandle const$2 = constants$20.const$0.varHandle(MemoryLayout.PathElement.groupElement("outgoingUnreliableSequenceNumber"));
  static final VarHandle const$3 = constants$20.const$0.varHandle(MemoryLayout.PathElement.groupElement("usedReliableWindows"));
  static final VarHandle const$4 = constants$20.const$0.varHandle(MemoryLayout.PathElement.groupElement("incomingReliableSequenceNumber"));
  static final VarHandle const$5 = constants$20.const$0.varHandle(MemoryLayout.PathElement.groupElement("incomingUnreliableSequenceNumber"));
}


