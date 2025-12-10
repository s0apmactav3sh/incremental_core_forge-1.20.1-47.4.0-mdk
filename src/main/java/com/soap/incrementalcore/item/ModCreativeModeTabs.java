package com.soap.incrementalcore.item;

import com.soap.incrementalcore.IncrementalCore;
import com.soap.incrementalcore.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IncrementalCore.MOD_ID);

    //Create custom items creative tab
    public static final RegistryObject<CreativeModeTab> STONE_ROD_ITEMS_TAB = CREATIVE_MODE_TABS.register("stone_rod_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.STONE_ROD.get()))
                    .title(Component.translatable("creativetab.incrementalcore.stone_rod"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.STONE_ROD.get());
                    }).build());

    //Create custom blocks creative tab
    public static final RegistryObject<CreativeModeTab> PRIMITIVE_CONSTRUCTION_FLOOR_TAB = CREATIVE_MODE_TABS.register("primitive_construction_floor_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PRIMITIVE_CONSTRUCTION_FLOOR.get()))
                    .withTabsBefore(STONE_ROD_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.incrementalcore.primitive_construction_floor"))
                    .displayItems((blockDisplayParameters, output) -> {
                        output.accept(ModBlocks.PRIMITIVE_CONSTRUCTION_FLOOR.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
