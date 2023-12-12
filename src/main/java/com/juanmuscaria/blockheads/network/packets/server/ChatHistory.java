package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import com.juanmuscaria.blockheads.chat.ChatMessage;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.util.LinkedList;

@ToString
public class ChatHistory implements Packet {
  public static byte ID = 0x25;
  private final LinkedList<ChatMessage> messages = new LinkedList<>();

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {

  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var dict = BHHelper.<NSDictionary>parseProperty(remaining);
    var messageArray = (NSArray) dict.get("messages");
    for (NSObject message : messageArray.getArray()) {
      this.messages.add(ChatMessage.fromDictionary((NSDictionary) message));
    }

    var photoArray = (NSDictionary) dict.get("photos");
    //Packet.printDict(photoArray);
    //TODO: parse photos
  }
}
