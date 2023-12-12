package com.juanmuscaria.blockheads.network;

import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferOutputStream extends OutputStream {
  private final ByteBuffer buffer;

  public ByteBufferOutputStream(ByteBuffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void write(int i) {
    buffer.putInt(i);
  }
}
