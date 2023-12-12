package com.juanmuscaria.blockheads.old;

import org.lmdbjava.CursorIterable;
import org.lmdbjava.Env;
import org.lmdbjava.Txn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

// World db tables > main, dw, blocks
// server db tables > main
// lightBlocks db tables > main
public class Test {
  public static Logger logger = LoggerFactory.getLogger("TEST");

  public static void main(String... args) throws Exception {
    final File path = new File("./world/WORLD/lightBlocks");

    // We always need an Env. An Env owns a physical on-disk storage file. One
    // Env can store many different databases (ie sorted maps).
    final Env<ByteBuffer> env = Env.create()
      // LMDB also needs to know how large our DB might be. Over-estimating is OK.
      .setMapSize(10_485_760)
      // LMDB also needs to know how many DBs (Dbi) we want to store in this Env.
      .setMaxDbs(1)
      // Now let's open the Env. The same path can be concurrently opened and
      // used in different processes, but do not open the same path twice in
      // the same process at the same time.
      .open(path);
    //var db = env.openDbi((String) null);
    var db = env.openDbi("main");
    try (Txn<ByteBuffer> txn = env.txnRead()) {
      for (CursorIterable.KeyVal<ByteBuffer> val : db.iterate(txn)) {
        Files.write(Path.of("./data/world", new String(toArray(val.key()))), toArray(val.val()));
        //logger.info("{} > {}", new String(toArray(val.key())), new String(toArray(val.val())));
      }
    }
  }

  private static final byte[] toArray(ByteBuffer buf) {
    byte[] arr = new byte[buf.remaining()];
    buf.get(arr);
    return arr;
  }
}
