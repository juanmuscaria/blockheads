// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_BYTE;
import static java.lang.foreign.ValueLayout.JAVA_INT;
final class constants$48 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$48() {}

  static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
    "enet_host_bandwidth_throttle",
    constants$11.const$1
  );
  static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
    "enet_host_random_seed",
    constants$38.const$5
  );
  static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
    RuntimeHelper.POINTER,
    JAVA_BYTE,
    RuntimeHelper.POINTER
  );
  static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
    "enet_peer_send",
    constants$48.const$2
  );
  static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
    "enet_peer_receive",
    constants$11.const$3
  );
  static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
    "enet_peer_ping",
    constants$11.const$1
  );
}


