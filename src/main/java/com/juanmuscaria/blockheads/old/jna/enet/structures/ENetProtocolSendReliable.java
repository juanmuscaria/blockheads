package com.juanmuscaria.blockheads.old.jna.enet.structures;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * <i>native declaration : ./enet/protocol.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class ENetProtocolSendReliable extends Structure {
  /**
   * C type : ENetProtocolCommandHeader
   */
  public ENetProtocolCommandHeader header;
  /**
   * C type : enet_uint16
   */
  public short dataLength;

  public ENetProtocolSendReliable() {
    super();
  }

  /**
   * @param header     C type : ENetProtocolCommandHeader<br>
   * @param dataLength C type : enet_uint16
   */
  public ENetProtocolSendReliable(ENetProtocolCommandHeader header, short dataLength) {
    super();
    this.header = header;
    this.dataLength = dataLength;
  }

  public ENetProtocolSendReliable(Pointer peer) {
    super(peer);
  }

  protected List<String> getFieldOrder() {
    return Arrays.asList("header", "dataLength");
  }

  public static class ByReference extends ENetProtocolSendReliable implements Structure.ByReference {

  }

  public static class ByValue extends ENetProtocolSendReliable implements Structure.ByValue {

  }
}