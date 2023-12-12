// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * struct sockaddr_in6 {
 *     sa_family_t sin6_family;
 *     in_port_t sin6_port;
 *     uint32_t sin6_flowinfo;
 *     struct in6_addr sin6_addr;
 *     uint32_t sin6_scope_id;
 * };
 * }
 */
public class sockaddr_in6 {

    public static MemoryLayout $LAYOUT() {
        return constants$49.const$4;
    }
    public static VarHandle sin6_family$VH() {
        return constants$49.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sa_family_t sin6_family;
     * }
     */
    public static short sin6_family$get(MemorySegment seg) {
        return (short)constants$49.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sa_family_t sin6_family;
     * }
     */
    public static void sin6_family$set(MemorySegment seg, short x) {
        constants$49.const$5.set(seg, x);
    }
    public static short sin6_family$get(MemorySegment seg, long index) {
        return (short)constants$49.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void sin6_family$set(MemorySegment seg, long index, short x) {
        constants$49.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle sin6_port$VH() {
        return constants$50.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * in_port_t sin6_port;
     * }
     */
    public static short sin6_port$get(MemorySegment seg) {
        return (short)constants$50.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * in_port_t sin6_port;
     * }
     */
    public static void sin6_port$set(MemorySegment seg, short x) {
        constants$50.const$0.set(seg, x);
    }
    public static short sin6_port$get(MemorySegment seg, long index) {
        return (short)constants$50.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void sin6_port$set(MemorySegment seg, long index, short x) {
        constants$50.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle sin6_flowinfo$VH() {
        return constants$50.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * uint32_t sin6_flowinfo;
     * }
     */
    public static int sin6_flowinfo$get(MemorySegment seg) {
        return (int)constants$50.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * uint32_t sin6_flowinfo;
     * }
     */
    public static void sin6_flowinfo$set(MemorySegment seg, int x) {
        constants$50.const$1.set(seg, x);
    }
    public static int sin6_flowinfo$get(MemorySegment seg, long index) {
        return (int)constants$50.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void sin6_flowinfo$set(MemorySegment seg, long index, int x) {
        constants$50.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment sin6_addr$slice(MemorySegment seg) {
        return seg.asSlice(8, 16);
    }
    public static VarHandle sin6_scope_id$VH() {
        return constants$50.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * uint32_t sin6_scope_id;
     * }
     */
    public static int sin6_scope_id$get(MemorySegment seg) {
        return (int)constants$50.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * uint32_t sin6_scope_id;
     * }
     */
    public static void sin6_scope_id$set(MemorySegment seg, int x) {
        constants$50.const$2.set(seg, x);
    }
    public static int sin6_scope_id$get(MemorySegment seg, long index) {
        return (int)constants$50.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void sin6_scope_id$set(MemorySegment seg, long index, int x) {
        constants$50.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

