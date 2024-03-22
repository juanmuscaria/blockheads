package com.juanmuscaria.blockheads;

import com.juanmuscaria.blockheads.world.WorldInfo;
import org.lmdbjava.CursorIterable.KeyVal;
import org.lmdbjava.Env;
import org.lmdbjava.Txn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Dumps all data contained withing Blockheads world DBs
 */
public class WorldInspector {
  private static final Logger logger = LoggerFactory.getLogger("Inspector");
  private static final Path IM_LAZY = Path.of("./world/MAC_WORLD");
  private static final String[] DB_NAMES = new String[]{
    "lightBlocks",
    "server_db",
    "world_db"
  };

  public static void main(String... args) throws Exception {
    var worldDir = IM_LAZY;
    if (args.length > 0) {
      worldDir = Path.of(args[0]);
    }

    logger.info("World > {}", WorldInfo.fromPath(worldDir));

    for (String dbName : DB_NAMES) {
      var dbFolder = worldDir.resolve(dbName).toFile();
      logger.info("Entering DB {} at {}", dbName, dbFolder);

      try (Env<ByteBuffer> env = Env.create()
        .setMapSize(10_485_760)
        .setMaxDbs(10)
        .open(dbFolder)) {
        for (byte[] dbiName : env.getDbiNames()) {
          var stringName = new String(dbiName, StandardCharsets.UTF_8);
          var dumpDir = Path.of(worldDir.toString(), "db_dump", dbName, stringName);
          var db = env.openDbi(dbiName);

          logger.info("  > Entering DBi {}", stringName);
          Files.createDirectories(dumpDir);
          int amount = 0;
          try (Txn<ByteBuffer> txn = env.txnRead()) {
            for (KeyVal<ByteBuffer> entry : db.iterate(txn)) {
              // Some keys have slashes, remove them
              var key = StandardCharsets.UTF_8.decode(entry.key()).toString().replace("/", "_slash_");
              try (FileOutputStream fos = new FileOutputStream(dumpDir.resolve(key).toFile());
                   FileChannel fileChannel = fos.getChannel()) {
                fileChannel.write(entry.val());
              }
              amount++;
            }
            logger.info("    > Dumped {} entries into {}", amount, dumpDir);
          } catch (Throwable e) {
            logger.error("Unable to read DBi", e);
          }
        }
      }
    }
  }
}
