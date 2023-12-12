// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * struct __pthread_internal_list {
 *     struct __pthread_internal_list* __prev;
 *     struct __pthread_internal_list* __next;
 * };
 * }
 */
public class __pthread_internal_list {

    public static MemoryLayout $LAYOUT() {
        return constants$7.const$5;
    }
    public static VarHandle __prev$VH() {
        return constants$8.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * struct __pthread_internal_list* __prev;
     * }
     */
    public static MemorySegment __prev$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$8.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * struct __pthread_internal_list* __prev;
     * }
     */
    public static void __prev$set(MemorySegment seg, MemorySegment x) {
        constants$8.const$0.set(seg, x);
    }
    public static MemorySegment __prev$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$8.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void __prev$set(MemorySegment seg, long index, MemorySegment x) {
        constants$8.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle __next$VH() {
        return constants$8.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * struct __pthread_internal_list* __next;
     * }
     */
    public static MemorySegment __next$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$8.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * struct __pthread_internal_list* __next;
     * }
     */
    public static void __next$set(MemorySegment seg, MemorySegment x) {
        constants$8.const$1.set(seg, x);
    }
    public static MemorySegment __next$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$8.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void __next$set(MemorySegment seg, long index, MemorySegment x) {
        constants$8.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

