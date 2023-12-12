// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;

/**
 * {@snippet :
 * struct _ENetEvent {
 *     ENetEventType type;
 *     ENetPeer* peer;
 *     enet_uint8 channelID;
 *     enet_uint32 data;
 *     ENetPacket* packet;
 * };
 * }
 */
public class _ENetEvent {

    public static MemoryLayout $LAYOUT() {
        return constants$116.const$4;
    }
    public static VarHandle type$VH() {
        return constants$116.const$5;
    }
    /**
     * Getter for field:
     * {@snippet :
     * ENetEventType type;
     * }
     */
    public static int type$get(MemorySegment seg) {
        return (int)constants$116.const$5.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * ENetEventType type;
     * }
     */
    public static void type$set(MemorySegment seg, int x) {
        constants$116.const$5.set(seg, x);
    }
    public static int type$get(MemorySegment seg, long index) {
        return (int)constants$116.const$5.get(seg.asSlice(index*sizeof()));
    }
    public static void type$set(MemorySegment seg, long index, int x) {
        constants$116.const$5.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle peer$VH() {
        return constants$117.const$0;
    }
    /**
     * Getter for field:
     * {@snippet :
     * ENetPeer* peer;
     * }
     */
    public static MemorySegment peer$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$117.const$0.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * ENetPeer* peer;
     * }
     */
    public static void peer$set(MemorySegment seg, MemorySegment x) {
        constants$117.const$0.set(seg, x);
    }
    public static MemorySegment peer$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$117.const$0.get(seg.asSlice(index*sizeof()));
    }
    public static void peer$set(MemorySegment seg, long index, MemorySegment x) {
        constants$117.const$0.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle channelID$VH() {
        return constants$117.const$1;
    }
    /**
     * Getter for field:
     * {@snippet :
     * enet_uint8 channelID;
     * }
     */
    public static byte channelID$get(MemorySegment seg) {
        return (byte)constants$117.const$1.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * enet_uint8 channelID;
     * }
     */
    public static void channelID$set(MemorySegment seg, byte x) {
        constants$117.const$1.set(seg, x);
    }
    public static byte channelID$get(MemorySegment seg, long index) {
        return (byte)constants$117.const$1.get(seg.asSlice(index*sizeof()));
    }
    public static void channelID$set(MemorySegment seg, long index, byte x) {
        constants$117.const$1.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle data$VH() {
        return constants$117.const$2;
    }
    /**
     * Getter for field:
     * {@snippet :
     * enet_uint32 data;
     * }
     */
    public static int data$get(MemorySegment seg) {
        return (int)constants$117.const$2.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * enet_uint32 data;
     * }
     */
    public static void data$set(MemorySegment seg, int x) {
        constants$117.const$2.set(seg, x);
    }
    public static int data$get(MemorySegment seg, long index) {
        return (int)constants$117.const$2.get(seg.asSlice(index*sizeof()));
    }
    public static void data$set(MemorySegment seg, long index, int x) {
        constants$117.const$2.set(seg.asSlice(index*sizeof()), x);
    }
    public static VarHandle packet$VH() {
        return constants$117.const$3;
    }
    /**
     * Getter for field:
     * {@snippet :
     * ENetPacket* packet;
     * }
     */
    public static MemorySegment packet$get(MemorySegment seg) {
        return (java.lang.foreign.MemorySegment)constants$117.const$3.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * ENetPacket* packet;
     * }
     */
    public static void packet$set(MemorySegment seg, MemorySegment x) {
        constants$117.const$3.set(seg, x);
    }
    public static MemorySegment packet$get(MemorySegment seg, long index) {
        return (java.lang.foreign.MemorySegment)constants$117.const$3.get(seg.asSlice(index*sizeof()));
    }
    public static void packet$set(MemorySegment seg, long index, MemorySegment x) {
        constants$117.const$3.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, Arena arena) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, arena); }
}

