// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
final class constants$31 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$31() {}

  static final MethodHandle const$0 = RuntimeHelper.upcallHandle(_ENetCompressor.decompress.class, "apply", constants$30.const$5);
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
      constants$30.const$5
    );
  static final VarHandle const$2 = constants$29.const$5.varHandle(MemoryLayout.PathElement.groupElement("decompress"));
  static final MethodHandle const$3 = RuntimeHelper.upcallHandle(_ENetCompressor.destroy.class, "apply", constants$11.const$1);
  static final VarHandle const$4 = constants$29.const$5.varHandle(MemoryLayout.PathElement.groupElement("destroy"));
  static final FunctionDescriptor const$5 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_LONG
    );
}


