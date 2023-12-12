package com.juanmuscaria.blockheads.chat;

import com.dd.plist.NSDictionary;
import com.juanmuscaria.blockheads.network.BHHelper;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@ToString
public class ChatMessage {
  private String alias;
  private Date date;
  private String message;
  private UUID playerID;

  public ChatMessage() {

  }

  public static ChatMessage fromDictionary(NSDictionary dictionary) {
    var chatMessage = new ChatMessage();
    chatMessage.alias = dictionary.get("alias").toJavaObject(String.class);
    chatMessage.date = (Date) dictionary.get("date").toJavaObject();
    chatMessage.message = dictionary.get("message").toJavaObject(String.class);
    chatMessage.playerID = UUID.fromString(BHHelper.toJavaUuid(dictionary.get("playerID").toJavaObject(String.class)));
    return chatMessage;
  }
}
