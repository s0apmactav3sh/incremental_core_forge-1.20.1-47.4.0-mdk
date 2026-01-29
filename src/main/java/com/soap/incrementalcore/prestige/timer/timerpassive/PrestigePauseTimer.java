package com.soap.incrementalcore.prestige.timer.timerpassive;

import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrestigePauseTimer {
    private final List<Long> pauseSegments = new ArrayList<>();

    private long pauseStartTick;
    private boolean paused;

    private final MinecraftServer server;

    public PrestigePauseTimer(MinecraftServer server) {
        this.server = server;
    }

    public void pause(MinecraftServer server) {
        if (paused) return;
        pauseStartTick = server.getTickCount();
        paused = true;
    }

    public void resume(MinecraftServer server) {
        if (!paused) return;
        long pausedTicks = server.getTickCount() - pauseStartTick;
        if (pausedTicks > 30) {
            pauseSegments.add(pausedTicks);
        }
        paused = false;
    }

    public long getTotalPausedTicks() {
        long total = 0;

        for (Long pausedTicks : pauseSegments) {
            total += pausedTicks;
        }

        return total;
    }

    public List<Long> getPauseSegments() {
        return Collections.unmodifiableList(pauseSegments);
    }

    public boolean isPaused() {
        return paused;
    }

    public void reset() {
        paused = false;
        pauseSegments.clear();
    }
}
