// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.ValueLayout.JAVA_LONG;
final class constants$43 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$43() {}

  static final FunctionDescriptor const$0 = FunctionDescriptor.of(JAVA_INT,
        JAVA_INT,
    RuntimeHelper.POINTER,
    RuntimeHelper.POINTER,
        JAVA_INT
    );
  static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
    "enet_socketset_select",
    constants$43.const$0
  );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
      "enet_address_set_host_ip",
      constants$32.const$2
    );
  static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
    "enet_address_set_host",
    constants$32.const$2
    );
  static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_INT,
    RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
    JAVA_LONG
    );
  static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
    "enet_address_get_host_ip",
    constants$43.const$4
    );
}


