package io.github.juanmuscaria.blockheads;

import io.github.juanmuscaria.blockheads.network.ENetServer;

public class BHServer {
    private ENetServer eNetServer;
    protected volatile boolean isRunning = true;

    public BHServer(){
        eNetServer = new ENetServer(null,15152);
    }

    public void runServerLoop(){
        while (isRunning){
            eNetServer.processEvents();
        }
    }

    public synchronized void stopServer(){
        isRunning = false;
    }
}
