package com.juanmuscaria.blockheads.old.jna.types;

import com.sun.jna.IntegerType;

public class Uint8_t extends IntegerType {
  public Uint8_t() {
    this(0);
  }

  public Uint8_t(int value) {
    super(1, value, true);
  }
}