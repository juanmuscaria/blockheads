// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$12 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$12() {}
    static final StructLayout const$0 = MemoryLayout.structLayout(
        MemoryLayout.unionLayout(
            JAVA_LONG.withName("__value64"),
            MemoryLayout.structLayout(
                JAVA_INT.withName("__low"),
                JAVA_INT.withName("__high")
            ).withName("__value32")
        ).withName("__wseq"),
        MemoryLayout.unionLayout(
            JAVA_LONG.withName("__value64"),
            MemoryLayout.structLayout(
                JAVA_INT.withName("__low"),
                JAVA_INT.withName("__high")
            ).withName("__value32")
        ).withName("__g1_start"),
        MemoryLayout.sequenceLayout(2, JAVA_INT).withName("__g_refs"),
        MemoryLayout.sequenceLayout(2, JAVA_INT).withName("__g_size"),
        JAVA_INT.withName("__g1_orig_size"),
        JAVA_INT.withName("__wrefs"),
        MemoryLayout.sequenceLayout(2, JAVA_INT).withName("__g_signals")
    ).withName("__pthread_cond_s");
    static final VarHandle const$1 = constants$12.const$0.varHandle(MemoryLayout.PathElement.groupElement("__g1_orig_size"));
    static final VarHandle const$2 = constants$12.const$0.varHandle(MemoryLayout.PathElement.groupElement("__wrefs"));
    static final StructLayout const$3 = MemoryLayout.structLayout(
        JAVA_INT.withName("__data")
    ).withName("");
    static final VarHandle const$4 = constants$12.const$3.varHandle(MemoryLayout.PathElement.groupElement("__data"));
    static final UnionLayout const$5 = MemoryLayout.unionLayout(
        MemoryLayout.sequenceLayout(4, JAVA_BYTE).withName("__size"),
        JAVA_INT.withName("__align")
    ).withName("");
}

