package com.juanmuscaria.blockheads.network.packets;

import com.dd.plist.NSArray;
import com.dd.plist.NSData;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSObject;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.Map;

public interface Packet {
  Logger logger = LoggerFactory.getLogger(Packet.class);

  @SneakyThrows
  static void printDict(NSDictionary dict) {
    printDict(dict, 0);
  }

  @SneakyThrows
  static void printDict(NSDictionary dict, int depth) {
    logger.info("  ".repeat(depth) + "NSDictionary {");
    for (Map.Entry<String, NSObject> stringNSObjectEntry : dict.getHashMap().entrySet()) {
      Object value = stringNSObjectEntry.getValue();
      if (value instanceof NSData data) {
        //var dBytes = new ByteArrayOutputStream();
        //new GZIPInputStream(new ByteArrayInputStream(data.bytes())).transferTo(dBytes);
        value = HexFormat.of().formatHex(data.bytes());
      } else if (value instanceof NSDictionary data) {
        logger.info("  ".repeat(depth + 1) + "{}:", stringNSObjectEntry.getKey());
        printDict(data, depth + 1);
        continue;
      } else if (value instanceof NSArray data) {
        value = Arrays.toString(data.getArray());
      }
      logger.info("  ".repeat(depth + 1) + "({}){}: {}", stringNSObjectEntry.getValue().getClass().getSimpleName(), stringNSObjectEntry.getKey(), value);
    }
    logger.info("  ".repeat(depth) + "}");
  }

  byte getId();

  void encode(ByteBuffer buffer);

  void decode(ByteBuffer buffer) throws Exception;
}
