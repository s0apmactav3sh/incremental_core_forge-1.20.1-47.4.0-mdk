package com.soap.incrementalcore.prestige.timer.countdowndisplay;

import java.util.List;

import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigePauseTimer;
import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigeRunTimer;
import com.soap.incrementalcore.prestige.timer.timerpassive.PrestigeTimerCalculator;
import com.soap.incrementalcore.prestige.timer.timerrules.PrestigeTimerController;
import com.soap.incrementalcore.prestige.util.TimeFormatUtil;
import net.minecraft.server.MinecraftServer;

import static com.soap.incrementalcore.prestige.timer.timerpassive.PrestigeTimerCalculator.calculateEffectiveTicks;

public class CountdownDisplay {

    //long maxRunTime = 20 * 60 * "PrestigeTimerController.timeAllowed"
    private final PrestigeRunTimer runTimer;
    private final PrestigePauseTimer pauseTimer;
    private final MinecraftServer server;
    private final PrestigeTimerController timerController;

    public CountdownDisplay(PrestigeRunTimer runTimer,
                            PrestigePauseTimer pauseTimer,
                            MinecraftServer server, PrestigeTimerController timerController) {
        this.runTimer = runTimer;
        this.pauseTimer = pauseTimer;
        this.server = server;
        this.timerController = timerController;
    }

    public String getTimeLeftDisplay() {
        long effectiveTicks = PrestigeTimerCalculator.calculateEffectiveTicks(runTimer, pauseTimer, server);
        long maxTicks = timerController.getMaxRuntimeTicks();

        long timeLeftTicks = Math.max(0, maxTicks - effectiveTicks);
        return TimeFormatUtil.ticksMMSS(timeLeftTicks);
    }
}
