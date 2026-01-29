package com.soap.incrementalcore.prestige.timer.timerpassive;

import net.minecraft.server.MinecraftServer;


public class PrestigeTimerCalculator {
    //static: method does not belong to instance, but class itself
    private final MinecraftServer server;

    public PrestigeTimerCalculator(MinecraftServer server) {
        this.server = server;
    }
    public static long calculateEffectiveTicks(
            PrestigeRunTimer runTimer,
            PrestigePauseTimer pauseTimer,
            MinecraftServer server) {
        long start = runTimer.getStartTick();
        long end = runTimer.getEndTick(server);

        long raw = end - start;
        long paused = pauseTimer.getTotalPausedTicks();

        long effective = raw - paused;

        if (effective < 0) {
            effective = 0;
        }

        return effective;
    }
}
