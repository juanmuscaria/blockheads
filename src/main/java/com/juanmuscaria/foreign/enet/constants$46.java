// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_INT;
final class constants$46 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$46() {}

  static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
  static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
    "enet_host_service",
    constants$46.const$0
  );
  static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
    "enet_host_flush",
    constants$11.const$1
  );
  static final FunctionDescriptor const$3 = FunctionDescriptor.ofVoid(
    RuntimeHelper.POINTER,
    JAVA_BYTE,
    RuntimeHelper.POINTER
  );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
      "enet_host_broadcast",
        constants$46.const$3
    );
  static final FunctionDescriptor const$5 = FunctionDescriptor.ofVoid(
    RuntimeHelper.POINTER,
    RuntimeHelper.POINTER
    );
    static final MethodHandle const$6 = RuntimeHelper.downcallHandle(
      "enet_host_compress",
        constants$46.const$5
    );
}


