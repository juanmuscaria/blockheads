// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.MethodHandle;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$120 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$120() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "enet_socket_wait",
        constants$43.const$5
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "enet_socket_set_option",
        constants$43.const$1
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
        JAVA_INT,
        JAVA_INT,
        RuntimeHelper.POINTER
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "enet_socket_get_option",
        constants$120.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "enet_socket_shutdown",
        constants$46.const$5
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "enet_socket_destroy",
        constants$15.const$0
    );
}

