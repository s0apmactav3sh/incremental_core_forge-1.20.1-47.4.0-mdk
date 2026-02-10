package com.soap.incrementalcore.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.soap.incrementalcore.content.tier0.primitiveworkbench.PrimitiveWorkbenchMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, GTCEu.MOD_ID);

    public static final RegistryObject<MenuType<PrimitiveWorkbenchMenu>> PRIMITIVE_WORKBENCH_MENU =
            registerMenuTypes("primitive_workbench_menu", PrimitiveWorkbenchMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuTypes(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
