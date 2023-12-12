package com.juanmuscaria.blockheads.network.packets.client;

import com.dd.plist.NSDictionary;
import com.dd.plist.XMLPropertyListParser;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.nio.ByteBuffer;

@ToString
public class ClientInformation implements Packet {
  public static final byte ID = 0x1F;
  String alias;
  String iCloudID;
  boolean local;
  boolean micOrSpeakerOn;
  int minorVersion;
  String playerID;
  String udidNew;
  boolean voiceConnected;

  @Override
  public byte getId() {
    return ID;
  }

  @Override
  public void encode(ByteBuffer buffer) {
    //TODO: Encoder
  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var dict = (NSDictionary) XMLPropertyListParser.parse(remaining);
    this.alias = dict.get("alias").toJavaObject(String.class);
    this.iCloudID = dict.get("iCloudID").toJavaObject(String.class);
    this.local = dict.get("local").toJavaObject(Boolean.class);
    this.micOrSpeakerOn = dict.get("micOrSpeakerOn").toJavaObject(Boolean.class);
    this.minorVersion = dict.get("minorVersion").toJavaObject(Integer.class);
    this.playerID = dict.get("playerID").toJavaObject(String.class);
    this.udidNew = dict.get("udidNew").toJavaObject(String.class);
    this.voiceConnected = dict.get("voiceConnected").toJavaObject(Boolean.class);
  }
}
