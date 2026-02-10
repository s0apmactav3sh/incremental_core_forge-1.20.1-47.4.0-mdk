package com.soap.incrementalcore.registry;

import com.gregtechceu.gtceu.GTCEu;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GTCEu.MOD_ID);

    //Create custom items creative tab
    public static final RegistryObject<CreativeModeTab> MILESTONE_CRAFTS_TAB = CREATIVE_MODE_TABS.register("milestone_crafts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WOODEN_WHEEL.get()))
                    .title(Component.translatable("creativetab.gtceu.wooden_wheel"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.WOODEN_WHEEL.get());
                    }).build());

    //Create custom blocks creative tab
    public static final RegistryObject<CreativeModeTab> MILESTONE_WORKBENCHES_TAB = CREATIVE_MODE_TABS.register("milestone_workbenches_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PRIMITIVE_WORKBENCH.get()))
                    .withTabsBefore(MILESTONE_CRAFTS_TAB.getId())
                    .title(Component.translatable("creativetab.gtceu.primitive_workbench"))
                    .displayItems((blockDisplayParameters, output) -> {
                        output.accept(ModBlocks.PRIMITIVE_WORKBENCH.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
