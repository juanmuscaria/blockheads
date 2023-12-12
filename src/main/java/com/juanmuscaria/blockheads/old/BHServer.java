package com.juanmuscaria.blockheads.old;

public class BHServer {
  private final ENetServer eNetServer;

  public BHServer(int port) {
    eNetServer = new ENetServer(null, port);
    while (true) {
      eNetServer.processEvents();
    }
  }
}
