package com.soap.incrementalcore.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.soap.incrementalcore.content.tier0.primitiveworkbench.PrimitiveWorkbenchBlock;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GTCEu.MOD_ID);

    public static final RegistryObject<Block> PRIMITIVE_CONSTRUCTION_FLOOR = registerBlock("primitive_construction_floor",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //Attempt to add primitive_workbench
    public static final RegistryObject<Block> PRIMITIVE_WORKBENCH = registerBlock("primitive_workbench",
            () -> new PrimitiveWorkbenchBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
