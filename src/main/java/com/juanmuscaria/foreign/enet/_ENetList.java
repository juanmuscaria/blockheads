// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.*;

/**
 * {@snippet :
 * struct _ENetList {
 *     ENetListNode sentinel;
 * };
 * }
 */
public class _ENetList {

    public static MemoryLayout $LAYOUT() {
        return constants$91.const$2;
    }
    public static MemorySegment sentinel$slice(MemorySegment seg) {
        return seg.asSlice(0, 16);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

