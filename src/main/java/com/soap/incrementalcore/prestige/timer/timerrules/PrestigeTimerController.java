package com.soap.incrementalcore.prestige.timer.timerrules;

import net.minecraft.server.MinecraftServer;

public class PrestigeTimerController {
    private int maxRunTime;

    private final MinecraftServer server;

    public PrestigeTimerController (MinecraftServer server) {
        this.server = server;
        this.maxRunTime = 1;
    }

    public double getMaxRunTimeMinutes() {
        return this.maxRunTime;
    }

    public long getMaxRuntimeTicks() {
        return maxRunTime * 60 * 20L;
    }
}
