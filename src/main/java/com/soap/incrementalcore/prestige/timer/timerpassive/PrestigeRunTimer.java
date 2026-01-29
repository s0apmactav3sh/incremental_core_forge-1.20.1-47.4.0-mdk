package com.soap.incrementalcore.prestige.timer.timerpassive;

import net.minecraft.server.MinecraftServer;

public class PrestigeRunTimer {

    private long startTick;
    private long endTick;
    private boolean running;

    private final MinecraftServer server;

    public PrestigeRunTimer(MinecraftServer server) {
        this.server = server;
    }


    public void start(MinecraftServer server) {
        startTick = server.getTickCount();
        running = true;
    }

    public void stop(MinecraftServer server) {
        if (!running) return;
        endTick = server.getTickCount();
        running = false;
    }

    public long getStartTick() {
        return startTick;
    }

    public long getEndTick(MinecraftServer server) {
        return running ? server.getTickCount() : endTick;
    }
}
