package com.soap.incrementalcore.prestige;

import com.soap.incrementalcore.prestige.run.RunState;
import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigePauseTimer;
import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigeRunTimer;
import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigeTimerCalculator;
import com.soap.incrementalcore.prestige.timer.timerrules.PrestigeTimerController;
import net.minecraft.server.MinecraftServer;

public class PrestigeRun {
    private RunState runState = RunState.NOT_STARTED;

    private static PrestigeRun INSTANCE;

    private final MinecraftServer server;
    private final PrestigeRunTimer runTimer;
    private final PrestigePauseTimer pauseTimer;
    private final PrestigeTimerCalculator timerCalculator;
    private final PrestigeTimerController timerController;

    private PrestigeRun(MinecraftServer server) {
        this.server = server;
        this.runTimer = new PrestigeRunTimer(server);
        this.pauseTimer = new PrestigePauseTimer(server);
        this.timerCalculator = new PrestigeTimerCalculator(server);
        this.timerController = new PrestigeTimerController(server);
    }

    public static PrestigeRun get(MinecraftServer server) {
        if (INSTANCE == null) {
            INSTANCE = new PrestigeRun(server);
        }
        return INSTANCE;
    }

    public void beginRun() {
        if(runState != RunState.NOT_STARTED) return;

        pauseTimer.reset();
        runTimer.start(server);
        runState = RunState.RUNNING;
        }

    public void endRun() {
        if(runState != RunState.RUNNING) return;

        runState = RunState.FINISHED;
    }

    public void armNextRun() {
        if (runState == RunState.FINISHED) {
            runState = RunState.NOT_STARTED;
        }
    }

    public void cancelNextRun() {
        if (runState == RunState.NOT_STARTED) {
            runState = RunState.FINISHED;
        }
    }

    public RunState getRunState() {
        return runState;
    }

    public boolean isRunning() {
        return runState == RunState.RUNNING;
    }

    public long getTimeLeft() {
        long effectiveTicks = PrestigeTimerCalculator.calculateEffectiveTicks(
                runTimer,
                pauseTimer,
                server
        );

        return timerController.getMaxRuntimeTicks() - effectiveTicks;
    }
}
