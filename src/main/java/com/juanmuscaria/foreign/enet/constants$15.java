// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.*;
final class constants$15 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$15() {}

  static final StructLayout const$0 = MemoryLayout.structLayout(
    JAVA_INT.withName("host"),
    JAVA_SHORT.withName("port"),
    MemoryLayout.paddingLayout(2)
  ).withName("_ENetAddress");
  static final VarHandle const$1 = constants$15.const$0.varHandle(MemoryLayout.PathElement.groupElement("host"));
  static final VarHandle const$2 = constants$15.const$0.varHandle(MemoryLayout.PathElement.groupElement("port"));
  static final MethodHandle const$3 = RuntimeHelper.upcallHandle(ENetPacketFreeCallback.class, "apply", constants$11.const$1);
  static final StructLayout const$4 = MemoryLayout.structLayout(
    JAVA_LONG.withName("referenceCount"),
    JAVA_INT.withName("flags"),
    MemoryLayout.paddingLayout(4),
    RuntimeHelper.POINTER.withName("data"),
    JAVA_LONG.withName("dataLength"),
    RuntimeHelper.POINTER.withName("freeCallback"),
    RuntimeHelper.POINTER.withName("userData")
  ).withName("_ENetPacket");
  static final VarHandle const$5 = constants$15.const$4.varHandle(MemoryLayout.PathElement.groupElement("referenceCount"));
}


