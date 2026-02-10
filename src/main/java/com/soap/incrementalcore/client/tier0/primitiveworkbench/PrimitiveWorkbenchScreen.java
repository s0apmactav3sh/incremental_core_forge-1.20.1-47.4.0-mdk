package com.soap.incrementalcore.client.tier0.primitiveworkbench;

import com.gregtechceu.gtceu.GTCEu;
import com.soap.incrementalcore.content.tier0.primitiveworkbench.PrimitiveWorkbenchMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PrimitiveWorkbenchScreen extends AbstractContainerScreen<PrimitiveWorkbenchMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    GTCEu.MOD_ID,
                    "textures/gui/primitive_workbench_gui.png"
            );

    public PrimitiveWorkbenchScreen(PrimitiveWorkbenchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.imageWidth = 176;
        this.imageHeight= 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = this.leftPos; // This is the centered X
        int y = this.topPos;  // This is the centered Y

        // Draw main box
        guiGraphics.blit(TEXTURE, x, y, 0, 0, 176, 166, 204, 166);

        // Draw Progress Arrow (even though it's "outside" the 176px imageWidth logic)
        renderProgressArrow(guiGraphics, x, y);
        renderDurabilityMachine(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 79, y + 33, 176, 0, menu.getScaledProgress(), 8, 204, 166);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void renderDurabilityMachine(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 81, y + 56, 176, 11, menu.getScaledDurability(), 3, 204, 166);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        // Leave empty to hide "Inventory" and "Primitive Workbench" text
    }


}
