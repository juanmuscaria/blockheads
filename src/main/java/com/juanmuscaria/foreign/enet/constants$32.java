// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.*;
final class constants$32 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$32() {}

  static final MethodHandle const$0 = RuntimeHelper.upcallHandle(ENetChecksumCallback.class, "apply", constants$31.const$5);
  static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        constants$31.const$5
    );
  static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
    RuntimeHelper.POINTER,
    RuntimeHelper.POINTER
    );
  static final MethodHandle const$3 = RuntimeHelper.upcallHandle(ENetInterceptCallback.class, "apply", constants$32.const$2);
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
      constants$32.const$2
    );
  static final StructLayout const$5 = MemoryLayout.structLayout(
    JAVA_INT.withName("socket"),
    MemoryLayout.structLayout(
      JAVA_INT.withName("host"),
      JAVA_SHORT.withName("port"),
      MemoryLayout.paddingLayout(2)
    ).withName("address"),
    JAVA_INT.withName("incomingBandwidth"),
    JAVA_INT.withName("outgoingBandwidth"),
    JAVA_INT.withName("bandwidthThrottleEpoch"),
    JAVA_INT.withName("mtu"),
    JAVA_INT.withName("randomSeed"),
    JAVA_INT.withName("recalculateBandwidthLimits"),
    MemoryLayout.paddingLayout(4),
    RuntimeHelper.POINTER.withName("peers"),
    JAVA_LONG.withName("peerCount"),
    JAVA_LONG.withName("channelLimit"),
    JAVA_INT.withName("serviceTime"),
    MemoryLayout.paddingLayout(4),
    MemoryLayout.structLayout(
      MemoryLayout.structLayout(
        RuntimeHelper.POINTER.withName("next"),
        RuntimeHelper.POINTER.withName("previous")
      ).withName("sentinel")
    ).withName("dispatchQueue"),
    JAVA_INT.withName("continueSending"),
    MemoryLayout.paddingLayout(4),
    JAVA_LONG.withName("packetSize"),
    JAVA_SHORT.withName("headerFlags"),
    MemoryLayout.sequenceLayout(32, MemoryLayout.unionLayout(
      MemoryLayout.structLayout(
        JAVA_BYTE.withName("command"),
        JAVA_BYTE.withName("channelID"),
        JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
      ).withName("header"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("receivedReliableSequenceNumber"),
        JAVA_SHORT.withByteAlignment(1).withName("receivedSentTime")
      ).withName("acknowledge"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("outgoingPeerID"),
        JAVA_BYTE.withName("incomingSessionID"),
        JAVA_BYTE.withName("outgoingSessionID"),
        JAVA_INT.withByteAlignment(1).withName("mtu"),
        JAVA_INT.withByteAlignment(1).withName("windowSize"),
        JAVA_INT.withByteAlignment(1).withName("channelCount"),
        JAVA_INT.withByteAlignment(1).withName("incomingBandwidth"),
        JAVA_INT.withByteAlignment(1).withName("outgoingBandwidth"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleInterval"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleAcceleration"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleDeceleration"),
        JAVA_INT.withByteAlignment(1).withName("connectID"),
        JAVA_INT.withByteAlignment(1).withName("data")
      ).withName("connect"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("outgoingPeerID"),
        JAVA_BYTE.withName("incomingSessionID"),
        JAVA_BYTE.withName("outgoingSessionID"),
        JAVA_INT.withByteAlignment(1).withName("mtu"),
        JAVA_INT.withByteAlignment(1).withName("windowSize"),
        JAVA_INT.withByteAlignment(1).withName("channelCount"),
        JAVA_INT.withByteAlignment(1).withName("incomingBandwidth"),
        JAVA_INT.withByteAlignment(1).withName("outgoingBandwidth"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleInterval"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleAcceleration"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleDeceleration"),
        JAVA_INT.withByteAlignment(1).withName("connectID")
      ).withName("verifyConnect"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_INT.withByteAlignment(1).withName("data")
      ).withName("disconnect"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header")
      ).withName("ping"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("dataLength")
      ).withName("sendReliable"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("unreliableSequenceNumber"),
        JAVA_SHORT.withByteAlignment(1).withName("dataLength")
      ).withName("sendUnreliable"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("unsequencedGroup"),
        JAVA_SHORT.withByteAlignment(1).withName("dataLength")
      ).withName("sendUnsequenced"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_SHORT.withByteAlignment(1).withName("startSequenceNumber"),
        JAVA_SHORT.withByteAlignment(1).withName("dataLength"),
        JAVA_INT.withByteAlignment(1).withName("fragmentCount"),
        JAVA_INT.withByteAlignment(1).withName("fragmentNumber"),
        JAVA_INT.withByteAlignment(1).withName("totalLength"),
        JAVA_INT.withByteAlignment(1).withName("fragmentOffset")
      ).withName("sendFragment"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_INT.withByteAlignment(1).withName("incomingBandwidth"),
        JAVA_INT.withByteAlignment(1).withName("outgoingBandwidth")
      ).withName("bandwidthLimit"),
      MemoryLayout.structLayout(
        MemoryLayout.structLayout(
          JAVA_BYTE.withName("command"),
          JAVA_BYTE.withName("channelID"),
          JAVA_SHORT.withByteAlignment(1).withName("reliableSequenceNumber")
        ).withName("header"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleInterval"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleAcceleration"),
        JAVA_INT.withByteAlignment(1).withName("packetThrottleDeceleration")
      ).withName("throttleConfigure")
    ).withName("_ENetProtocol")).withName("commands"),
    MemoryLayout.paddingLayout(6),
    JAVA_LONG.withName("commandCount"),
    MemoryLayout.sequenceLayout(65, MemoryLayout.structLayout(
      RuntimeHelper.POINTER.withName("data"),
      JAVA_LONG.withName("dataLength")
    ).withName("")).withName("buffers"),
    JAVA_LONG.withName("bufferCount"),
    RuntimeHelper.POINTER.withName("checksum"),
    MemoryLayout.structLayout(
      RuntimeHelper.POINTER.withName("context"),
      RuntimeHelper.POINTER.withName("compress"),
      RuntimeHelper.POINTER.withName("decompress"),
      RuntimeHelper.POINTER.withName("destroy")
    ).withName("compressor"),
    MemoryLayout.sequenceLayout(2, MemoryLayout.sequenceLayout(4096, JAVA_BYTE)).withName("packetData"),
    MemoryLayout.structLayout(
      JAVA_INT.withName("host"),
      JAVA_SHORT.withName("port"),
      MemoryLayout.paddingLayout(2)
    ).withName("receivedAddress"),
    RuntimeHelper.POINTER.withName("receivedData"),
    JAVA_LONG.withName("receivedDataLength"),
    JAVA_INT.withName("totalSentData"),
    JAVA_INT.withName("totalSentPackets"),
    JAVA_INT.withName("totalReceivedData"),
    JAVA_INT.withName("totalReceivedPackets"),
    RuntimeHelper.POINTER.withName("intercept"),
    JAVA_LONG.withName("connectedPeers"),
    JAVA_LONG.withName("bandwidthLimitedPeers"),
    JAVA_LONG.withName("duplicatePeers"),
    JAVA_LONG.withName("maximumPacketSize"),
    JAVA_LONG.withName("maximumWaitingData")
  ).withName("_ENetHost");
}


