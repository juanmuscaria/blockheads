package io.github.juanmuscaria.blockheads;

import io.github.juanmuscaria.blockheads.jna.enet.Enet;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetAddress;
import io.github.juanmuscaria.blockheads.jna.enet.structures.ENetHost;
import io.github.juanmuscaria.blockheads.jna.types.Size_t;

public class Main {
    public static void main(String[] args){
        new BHServer().runServerLoop();
    }
}
