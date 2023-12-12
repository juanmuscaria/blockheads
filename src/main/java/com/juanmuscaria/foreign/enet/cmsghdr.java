// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * struct cmsghdr {
 *     size_t cmsg_len;
 *     int cmsg_level;
 *     int cmsg_type;
 *     unsigned char __cmsg_data[];
 * };
 * }
 */
public class cmsghdr {

    public static MemoryLayout $LAYOUT() {
        return constants$40.const$5;
    }
    public static VarHandle cmsg_len$VH() {
        return constants$41.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * size_t cmsg_len;
     * }
     */
    public static long cmsg_len$get(MemorySegment seg) {
        return (long)constants$41.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * size_t cmsg_len;
     * }
     */
    public static void cmsg_len$set(MemorySegment seg, long x) {
        constants$41.const$0.set(seg, x);
    }
    public static long cmsg_len$get(MemorySegment seg, long index) {
        return (long)constants$41.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void cmsg_len$set(MemorySegment seg, long index, long x) {
        constants$41.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle cmsg_level$VH() {
        return constants$41.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int cmsg_level;
     * }
     */
    public static int cmsg_level$get(MemorySegment seg) {
        return (int)constants$41.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int cmsg_level;
     * }
     */
    public static void cmsg_level$set(MemorySegment seg, int x) {
        constants$41.const$1.set(seg, x);
    }
    public static int cmsg_level$get(MemorySegment seg, long index) {
        return (int)constants$41.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void cmsg_level$set(MemorySegment seg, long index, int x) {
        constants$41.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle cmsg_type$VH() {
        return constants$41.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * int cmsg_type;
     * }
     */
    public static int cmsg_type$get(MemorySegment seg) {
        return (int)constants$41.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * int cmsg_type;
     * }
     */
    public static void cmsg_type$set(MemorySegment seg, int x) {
        constants$41.const$2.set(seg, x);
    }
    public static int cmsg_type$get(MemorySegment seg, long index) {
        return (int)constants$41.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void cmsg_type$set(MemorySegment seg, long index, int x) {
        constants$41.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

