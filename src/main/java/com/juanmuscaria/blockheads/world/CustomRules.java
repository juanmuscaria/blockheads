package com.juanmuscaria.blockheads.world;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CustomRules {
  private String caves;
  private String clothingDecay;
  private String dayNightCycle;
  private String death;
  private String gems;
  private String happiness;
  private String health;
  private String hunger;
  private String lakes;
  private String maxBlockheads;
  private String npcMobDifficulty;
  private String oceans;
  private String oreDistribution;
  private String oreQuantity;
  private String plants;
  private String rainfall;
  private String seed;
  private String skyIslands;
  private List<SpawnItemSlot> spawnItemSlots;
  private int sunColor0;
  private int sunColor1;
  private int sunColor2;
  private String temperature;
  private String terrain;
  private String toolDecay;
  private String tradePortals;
  private String treasure;
  private String trees;


  public static CustomRules fromDictionary(NSDictionary customRulesDict) {
    var obj = new CustomRules();

    obj.caves = customRulesDict.objectForKey("caves").toString();
    obj.clothingDecay = customRulesDict.objectForKey("clothing decay").toString();
    obj.dayNightCycle = customRulesDict.objectForKey("day/night cycle").toString();
    obj.death = customRulesDict.objectForKey("death").toString();
    obj.gems = customRulesDict.objectForKey("gems").toString();
    obj.happiness = customRulesDict.objectForKey("happiness").toString();
    obj.health = customRulesDict.objectForKey("health").toString();
    obj.hunger = customRulesDict.objectForKey("hunger").toString();
    obj.lakes = customRulesDict.objectForKey("lakes").toString();
    obj.maxBlockheads = customRulesDict.objectForKey("max blockheads").toString();
    obj.npcMobDifficulty = customRulesDict.objectForKey("npc/mob difficulty").toString();
    obj.oceans = customRulesDict.objectForKey("oceans").toString();
    obj.oreDistribution = customRulesDict.objectForKey("ore distribution").toString();
    obj.oreQuantity = customRulesDict.objectForKey("ore quantity").toString();
    obj.plants = customRulesDict.objectForKey("plants").toString();
    obj.rainfall = customRulesDict.objectForKey("rainfall").toString();
    obj.seed = customRulesDict.objectForKey("seed").toString();
    obj.skyIslands = customRulesDict.objectForKey("sky islands").toString();

    // Extract spawn item slots
    obj.spawnItemSlots = extractSpawnItemSlots((NSArray) customRulesDict.objectForKey("spawnItemSlots"));

    obj.sunColor0 = ((NSNumber) customRulesDict.objectForKey("sunColor_0")).intValue();
    obj.sunColor1 = ((NSNumber) customRulesDict.objectForKey("sunColor_1")).intValue();
    obj.sunColor2 = ((NSNumber) customRulesDict.objectForKey("sunColor_2")).intValue();
    obj.temperature = customRulesDict.objectForKey("temperature").toString();
    obj.terrain = customRulesDict.objectForKey("terrain").toString();
    obj.toolDecay = customRulesDict.objectForKey("tool decay").toString();
    obj.tradePortals = customRulesDict.objectForKey("trade portals").toString();
    obj.treasure = customRulesDict.objectForKey("treasure").toString();
    obj.trees = customRulesDict.objectForKey("trees").toString();

    return obj;
  }

  private static List<SpawnItemSlot> extractSpawnItemSlots(NSArray spawnItemSlotsArray) {
    List<SpawnItemSlot> spawnItemSlots = new ArrayList<>();

    for (Object obj : spawnItemSlotsArray.getArray()) {
      NSDictionary spawnItemSlotDict = (NSDictionary) obj;
      int count = ((NSNumber) spawnItemSlotDict.objectForKey("count")).intValue();
      int type = ((NSNumber) spawnItemSlotDict.objectForKey("type")).intValue();
      SpawnItemSlot spawnItemSlot = new SpawnItemSlot(count, type);
      spawnItemSlots.add(spawnItemSlot);
    }

    return spawnItemSlots;
  }

  public record SpawnItemSlot(int count, int type) {
  }
}
