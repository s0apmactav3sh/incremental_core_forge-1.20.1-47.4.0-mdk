package com.soap.incrementalcore.prestige.run;

import com.soap.incrementalcore.prestige.PrestigeRun;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RunEnder {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        MinecraftServer server = event.getServer();
        PrestigeRun run = PrestigeRun.get(server);

        if (!run.isRunning()) return;

        if (run.getTimeLeft() <= 0) {
            run.endRun();
        }
    }
}
