package com.juanmuscaria.blockheads.world;

import com.dd.plist.*;
import com.juanmuscaria.blockheads.network.BHHelper;
import lombok.ToString;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

@ToString
public class WorldInfo {
  public static String WORLD_V2 = "worldv2";
  private NSArray blockheadDatas;
  private byte[] circumNavigateBooleansData;
  private Date creationDate;
  private CustomRules customRulesDict;
  private byte[] distanceOrderedFoodTypes;
  private String hostPort;
  private String maxPlayers;
  private double noRainTimer;
  private int portalLevel;
  private String privacy;
  private int randomSeed;
  private boolean remoteGame;
  private boolean runAtLaunch;
  private Date saveDate;
  private String saveID;
  private int saveVersion;
  private int startPortalPos_x;
  private int startPortalPos_y;
  private double[] translation;
  private String worldName;
  private double worldTime;
  private int worldWidthMacro;
  private boolean migrationComplete_1_7;
  private boolean expertMode;

  public static WorldInfo fromPath(Path worldDir) throws IOException, PropertyListFormatException, ParserConfigurationException, ParseException, SAXException {
    var worldplist = worldDir.resolve(WORLD_V2);
    if (Files.isRegularFile(worldplist)) {
      return fromDictionary(BHHelper.parseProperty(Files.readAllBytes(worldplist)));
    } else {
      throw new FileNotFoundException(worldplist.toString());
    }
  }


  public static WorldInfo fromDictionary(NSDictionary dictionary) {
    var obj = new WorldInfo();

    // Optional/legacy stuff?
    obj.blockheadDatas = (NSArray) dictionary.objectForKey("blockheadDatas");
    obj.blockheadDatas = dictionary.containsKey("blockheadDatas") ? (NSArray) dictionary.objectForKey("blockheadDatas") : null;
    obj.circumNavigateBooleansData = dictionary.containsKey("circumNavigateBooleansData") ? ((NSData) dictionary.objectForKey("circumNavigateBooleansData")).bytes() : null;
    obj.distanceOrderedFoodTypes = dictionary.containsKey("distanceOrderedFoodTypes") ? ((NSData) dictionary.objectForKey("distanceOrderedFoodTypes")).bytes() : null;
    obj.hostPort = dictionary.containsKey("hostPort") ? dictionary.objectForKey("hostPort").toString() : null;
    obj.maxPlayers = dictionary.containsKey("maxPlayers") ? dictionary.objectForKey("maxPlayers").toString() : null;
    obj.noRainTimer = dictionary.containsKey("noRainTimer") ? ((NSNumber) dictionary.objectForKey("noRainTimer")).doubleValue() : 0.0;
    obj.portalLevel = dictionary.containsKey("portalLevel") ? ((NSNumber) dictionary.objectForKey("portalLevel")).intValue() : 0;
    obj.privacy = dictionary.containsKey("privacy") ? dictionary.objectForKey("privacy").toString() : null;
    obj.randomSeed = dictionary.containsKey("randomSeed") ? ((NSNumber) dictionary.objectForKey("randomSeed")).intValue() : 0;
    obj.remoteGame = dictionary.containsKey("remoteGame") && ((NSNumber) dictionary.objectForKey("remoteGame")).boolValue();
    obj.runAtLaunch = dictionary.containsKey("runAtLaunch") && ((NSNumber) dictionary.objectForKey("runAtLaunch")).boolValue();
    obj.startPortalPos_x = dictionary.containsKey("startPortalPos.x") ? ((NSNumber) dictionary.objectForKey("startPortalPos.x")).intValue() : 0;
    obj.startPortalPos_y = dictionary.containsKey("startPortalPos.y") ? ((NSNumber) dictionary.objectForKey("startPortalPos.y")).intValue() : 0;
    obj.worldTime = dictionary.containsKey("worldTime") ? ((NSNumber) dictionary.objectForKey("worldTime")).doubleValue() : 0;
    obj.expertMode = dictionary.containsKey("expertMode") && ((NSNumber) dictionary.objectForKey("expertMode")).boolValue();
    obj.migrationComplete_1_7 = !dictionary.containsKey("migrationComplete_1.7") || ((NSNumber) dictionary.objectForKey("migrationComplete_1.7")).boolValue();

    if (dictionary.containsKey("translation")) {
      obj.translation = Arrays.stream(((NSArray) dictionary.objectForKey("translation")).getArray())
        .map(num -> ((NSNumber) num).doubleValue())
        .mapToDouble(Double::doubleValue)
        .toArray();
    }

    // Always present, I think
    obj.saveID = dictionary.objectForKey("saveID").toString();
    obj.worldName = dictionary.objectForKey("worldName").toString();
    obj.worldWidthMacro = ((NSNumber) dictionary.objectForKey("worldWidthMacro")).intValue();
    obj.creationDate = ((NSDate) dictionary.objectForKey("creationDate")).getDate();
    obj.saveDate = ((NSDate) dictionary.objectForKey("saveDate")).getDate();
    obj.saveVersion = ((NSNumber) dictionary.objectForKey("saveVersion")).intValue();

    // Custom rules data
    if (dictionary.containsKey("customRulesDict")) {
      obj.customRulesDict = CustomRules.fromDictionary((NSDictionary) dictionary.objectForKey("customRulesDict"));
    }

    return obj;
  }
}
