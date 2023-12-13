// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.VarHandle;

/**
 * {@snippet :
 * struct _ENetProtocolSendUnreliable {
 *     ENetProtocolCommandHeader header;
 *     enet_uint16 unreliableSequenceNumber;
 *     enet_uint16 dataLength;
 * };
 * }
 */
public class _ENetProtocolSendUnreliable {

    public static MemoryLayout $LAYOUT() {
      return constants$8.const$1;
    }
    public static MemorySegment header$slice(MemorySegment seg) {
        return seg.asSlice(0, 4);
    }
    public static VarHandle unreliableSequenceNumber$VH() {
      return constants$8.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * enet_uint16 unreliableSequenceNumber;
     * }
     */
    public static short unreliableSequenceNumber$get(MemorySegment seg) {
      return (short) constants$8.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * enet_uint16 unreliableSequenceNumber;
     * }
     */
    public static void unreliableSequenceNumber$set(MemorySegment seg, short x) {
      constants$8.const$2.set(seg, x);
    }
    public static short unreliableSequenceNumber$get(MemorySegment seg, long index) {
      return (short) constants$8.const$2.get(seg.asSlice(index * sizeof()));
    }
    public static void unreliableSequenceNumber$set(MemorySegment seg, long index, short x) {
      constants$8.const$2.set(seg.asSlice(index * sizeof()), x);
    }
    public static VarHandle dataLength$VH() {
      return constants$8.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * enet_uint16 dataLength;
     * }
     */
    public static short dataLength$get(MemorySegment seg) {
      return (short) constants$8.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * enet_uint16 dataLength;
     * }
     */
    public static void dataLength$set(MemorySegment seg, short x) {
      constants$8.const$3.set(seg, x);
    }
    public static short dataLength$get(MemorySegment seg, long index) {
      return (short) constants$8.const$3.get(seg.asSlice(index * sizeof()));
    }
    public static void dataLength$set(MemorySegment seg, long index, short x) {
      constants$8.const$3.set(seg.asSlice(index * sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}


