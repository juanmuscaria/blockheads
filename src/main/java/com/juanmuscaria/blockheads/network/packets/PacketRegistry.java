package com.juanmuscaria.blockheads.network.packets;

import com.juanmuscaria.blockheads.network.packets.client.*;
import com.juanmuscaria.blockheads.network.packets.server.*;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import lombok.SneakyThrows;

import java.nio.ByteBuffer;

public class PacketRegistry {
  private static final Byte2ObjectOpenHashMap<Class<? extends Packet>> serverPackets = new Byte2ObjectOpenHashMap<>();
  private static final Byte2ObjectOpenHashMap<Class<? extends Packet>> clientPackets = new Byte2ObjectOpenHashMap<>();

  static {
    serverPackets.put(WorldId.ID, WorldId.class);
    serverPackets.put(ServerInformation.ID, ServerInformation.class);
    serverPackets.put(WorldFragment.ID, WorldFragment.class);
    serverPackets.put(BlockheadsData.ID, BlockheadsData.class);
    serverPackets.put(ChatHistory.ID, ChatHistory.class);
    serverPackets.put(CreateObjects.ID, CreateObjects.class);
    serverPackets.put(UpdateObjects.ID, UpdateObjects.class);
    serverPackets.put(RemoveObjects.ID, RemoveObjects.class);
    serverPackets.put(KeepAliveResponse.ID, KeepAliveResponse.class);

    clientPackets.put(ClientInformation.ID, ClientInformation.class);
    clientPackets.put(RequestWorldFragment.ID, RequestWorldFragment.class);
    clientPackets.put(RequestChatHistory.ID, RequestChatHistory.class);
    clientPackets.put(RequestCreateObjects.ID, RequestCreateObjects.class);
    clientPackets.put(KeepAlive.ID, KeepAlive.class);
    clientPackets.put(UpdatePlayerActionsAndState.ID, UpdatePlayerActionsAndState.class);
    clientPackets.put(RequestRemoveObjects.ID, RequestRemoveObjects.class);
    clientPackets.put(UpdatePlayerInventory.ID, UpdatePlayerInventory.class);
  }

  @SneakyThrows(ReflectiveOperationException.class)
  public static Packet parseClientPacket(ByteBuffer buffer) {
    byte id = buffer.get();
    var packetClass = clientPackets.get(id);
    if (packetClass != null) {
      var packet = packetClass.getConstructor().newInstance();
      try {
        packet.decode(buffer);
        return packet;
      } catch (Exception e) {
        Packet.logger.error("Failed to parse packet", e);
      }
    }
    return null;
  }

  @SneakyThrows(ReflectiveOperationException.class)
  public static Packet parseServerPacket(ByteBuffer buffer) {
    byte id = buffer.get();
    var packetClass = serverPackets.get(id);
    if (packetClass != null) {
      var packet = packetClass.getConstructor().newInstance();
      try {
        packet.decode(buffer);
        return packet;
      } catch (Exception e) {
        Packet.logger.error("Failed to parse packet", e);
      }
    }
    return null;
  }
}
