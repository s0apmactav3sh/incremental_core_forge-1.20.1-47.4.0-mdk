package com.soap.incrementalcore.prestige.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.soap.incrementalcore.prestige.PrestigeRun;
import com.soap.incrementalcore.prestige.util.TimeFormatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class CountdownOverlay {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        MinecraftServer server = mc.getSingleplayerServer();
        if (server == null) return; // multiplayer-safe guard

        PrestigeRun run = PrestigeRun.get(server);
        if (!run.isRunning()) return;

        long timeLeftTicks = run.getTimeLeft();
        String timeText = TimeFormatUtil.ticksMMSS(timeLeftTicks);

        GuiGraphics gui = event.getGuiGraphics();

        int x = 10;
        int y = gui.guiHeight() - 20;

        gui.drawString(
                mc.font,
                "Time Remaining: " + timeText,
                x,
                y,
                0xFFFFFF
        );
    }
}
