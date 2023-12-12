// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * union {
 *     char __size[4];
 *     int __align;
 * };
 * }
 */
public class pthread_mutexattr_t {

    public static MemoryLayout $LAYOUT() {
        return constants$12.const$5;
    }
    public static MemorySegment __size$slice(MemorySegment seg) {
        return seg.asSlice(0, 4);
    }
    public static VarHandle __align$VH() {
        return constants$13.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int __align;
     * }
     */
    public static int __align$get(MemorySegment seg) {
        return (int)constants$13.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int __align;
     * }
     */
    public static void __align$set(MemorySegment seg, int x) {
        constants$13.const$0.set(seg, x);
    }
    public static int __align$get(MemorySegment seg, long index) {
        return (int)constants$13.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void __align$set(MemorySegment seg, long index, int x) {
        constants$13.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

