package com.juanmuscaria.blockheads.network.packets.server;

import com.dd.plist.BinaryPropertyListWriter;
import com.dd.plist.NSDictionary;
import com.juanmuscaria.blockheads.network.BHHelper;
import com.juanmuscaria.blockheads.network.ByteBufferOutputStream;
import com.juanmuscaria.blockheads.network.packets.Packet;
import lombok.SneakyThrows;
import lombok.ToString;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

import static com.juanmuscaria.blockheads.network.BHHelper.toBhUuid;
import static com.juanmuscaria.blockheads.network.BHHelper.toJavaUuid;

@ToString
public class WorldId extends Packet {
  public static final byte ID = 0x23;
  public static final String FAKE_UUID = "84a23c0ba0f44633b05d5d0242b1a5e7";
  private UUID worldId;

  public WorldId() {
    this.worldId = UUID.fromString(toJavaUuid(FAKE_UUID));
  }

  public WorldId(String worldId) {
    this.worldId = UUID.fromString(toJavaUuid(worldId));
  }

  @SneakyThrows(IOException.class) // Impossible exception
  @Override
  public void encode(ByteBuffer buffer) {
    buffer.put(ID); // Packet id
    buffer.putChar('&'); // Not sure what this is supposed to mean, but seems to be required?
    var dict = new NSDictionary();
    dict.put("worldID", toBhUuid(this.worldId.toString()));
    BinaryPropertyListWriter.write(dict, new ByteBufferOutputStream(buffer)); // The actual packet data
  }

  @Override
  public void decode(ByteBuffer buffer) throws Exception {
    if (buffer.get() != '&') {
      logger.warn("invalid {} packet?'", this.getClass());
    }
    byte[] remaining = new byte[buffer.remaining()];
    buffer.get(remaining);
    var dict = BHHelper.<NSDictionary>parseProperty(remaining);
    this.worldId = UUID.fromString(toJavaUuid(dict.get("worldID").toString()));
  }
}
