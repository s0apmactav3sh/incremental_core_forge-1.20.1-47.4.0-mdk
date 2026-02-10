package com.soap.incrementalcore.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.soap.incrementalcore.content.tier0.primitiveworkbench.PrimitiveWorkbenchBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GTCEu.MOD_ID);

    public static final RegistryObject<BlockEntityType<PrimitiveWorkbenchBlockEntity>> PRIMITIVE_WORKBENCH_BE =
            BLOCK_ENTITIES.register("primitive_workbench_be", () ->
                    BlockEntityType.Builder.of(PrimitiveWorkbenchBlockEntity::new,
                            ModBlocks.PRIMITIVE_WORKBENCH.get()).build(null));

    public static void register(IEventBus eventBus) { BLOCK_ENTITIES.register(eventBus); }
}
