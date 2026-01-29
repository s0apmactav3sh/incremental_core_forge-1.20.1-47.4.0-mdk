package com.soap.incrementalcore.item;

import com.gregtechceu.gtceu.GTCEu;
import com.soap.incrementalcore.IncrementalCore;
import com.soap.incrementalcore.item.base.ModItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GTCEu.MOD_ID);

    public static final RegistryObject<Item> FLINT_BOLT = ITEMS.register("flint_bolt",
            () -> new ModItem(new Item.Properties()));
    public static final RegistryObject<Item> GROUT = ITEMS.register("grout",
            () -> new ModItem(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_WHEEL = ITEMS.register("wooden_wheel",
            () -> new ModItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
