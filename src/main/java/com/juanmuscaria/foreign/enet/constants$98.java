// Generated by jextract

package com.juanmuscaria.foreign.enet;

import java.lang.invoke.VarHandle;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$98 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$98() {}
    static final VarHandle const$0 = constants$96.const$3.varHandle(MemoryLayout.PathElement.groupElement("packet"));
    static final StructLayout const$1 = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            RuntimeHelper.POINTER.withName("next"),
            RuntimeHelper.POINTER.withName("previous")
        ).withName("incomingCommandList"),
        JAVA_SHORT.withName("reliableSequenceNumber"),
        JAVA_SHORT.withName("unreliableSequenceNumber"),
        MemoryLayout.unionLayout(
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
        ).withName("command"),
        JAVA_INT.withName("fragmentCount"),
        JAVA_INT.withName("fragmentsRemaining"),
        MemoryLayout.paddingLayout(4),
        RuntimeHelper.POINTER.withName("fragments"),
        RuntimeHelper.POINTER.withName("packet")
    ).withName("_ENetIncomingCommand");
    static final VarHandle const$2 = constants$98.const$1.varHandle(MemoryLayout.PathElement.groupElement("reliableSequenceNumber"));
    static final VarHandle const$3 = constants$98.const$1.varHandle(MemoryLayout.PathElement.groupElement("unreliableSequenceNumber"));
    static final VarHandle const$4 = constants$98.const$1.varHandle(MemoryLayout.PathElement.groupElement("fragmentCount"));
    static final VarHandle const$5 = constants$98.const$1.varHandle(MemoryLayout.PathElement.groupElement("fragmentsRemaining"));
}

