// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.MethodHandle;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$54 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$54() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "htons",
        constants$53.const$3
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "bindresvport",
        constants$17.const$4
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "bindresvport6",
        constants$17.const$4
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "inet_addr",
        constants$2.const$0
    );
    static final FunctionDescriptor const$4 = FunctionDescriptor.of(JAVA_INT,
        MemoryLayout.structLayout(
            JAVA_INT.withName("s_addr")
        ).withName("in_addr")
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "inet_lnaof",
        constants$54.const$4
    );
}

