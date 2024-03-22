package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.NSDictionary;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.ToString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;

@ToString
public class ServerInformation extends Packet {
  public static byte ID = 0x01;
  private double worldTime;    // worldTime
  private String worldName;    // worldName
  private int startPortalPosX; // startPortalPos.x
  private int startPortalPosY; // startPortalPos.y
  private int portalLevel;     // portalLevel
  private int highestPointX;   // highestPoint.x
  private int highestPointY;   // highestPoint.y
  private double credit;       // credit
  private String saveId;       // saveID
  private long randomSeed;     // randomSeed
  private double noRainTimer;  // noRainTimer
  private int worldWidthMacro; // worldWidthMacro
  private boolean expertMode;  // expertMode
  private int minorVersion;    // minorVersion

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var data = new ByteArrayOutputStream();
    new GZIPInputStream(new ByteArrayInputStream(remaining)).transferTo(data);
    var dict = BHHelper.<NSDictionary>parseProperty(data.toByteArray());
    worldTime = dict.get("worldTime").toJavaObject(Double.class);
    worldName = dict.get("worldName").toJavaObject(String.class);
    startPortalPosX = dict.get("startPortalPos.x").toJavaObject(Integer.class);
    startPortalPosY = dict.get("startPortalPos.y").toJavaObject(Integer.class);
    portalLevel = dict.get("portalLevel").toJavaObject(Integer.class);
    highestPointX = dict.get("highestPoint.x").toJavaObject(Integer.class);
    highestPointY = dict.get("highestPoint.y").toJavaObject(Integer.class);
    credit = dict.get("credit").toJavaObject(Double.class);
    saveId = dict.get("saveID").toJavaObject(String.class);
    randomSeed = dict.get("randomSeed").toJavaObject(Long.class);
    noRainTimer = dict.get("noRainTimer").toJavaObject(Double.class);
    worldWidthMacro = dict.get("worldWidthMacro").toJavaObject(Integer.class);
    expertMode = dict.get("expertMode").toJavaObject(Boolean.class);
    minorVersion = dict.get("minorVersion").toJavaObject(Integer.class);
  }
}
