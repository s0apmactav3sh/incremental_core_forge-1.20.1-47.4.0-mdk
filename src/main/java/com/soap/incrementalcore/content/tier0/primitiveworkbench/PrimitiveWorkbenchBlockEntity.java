package com.soap.incrementalcore.content.tier0.primitiveworkbench;

import com.soap.incrementalcore.registry.ModBlockEntities;
import com.soap.incrementalcore.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrimitiveWorkbenchBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4);
    private static final int INPUT_SLOT1 = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int INPUT_SLOT3 = 2;
    private static final int OUTPUT_SLOT1 = 3;

    private static final TagKey<Item> STICKY_RESIN_TAG = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("gtceu", "sticky_resin")
    );

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 600;
    private int durability = 16;
    private int maxDurability = 16;

    public PrimitiveWorkbenchBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.PRIMITIVE_WORKBENCH_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch(pIndex) {
                    case 0 -> PrimitiveWorkbenchBlockEntity.this.progress;
                    case 1 -> PrimitiveWorkbenchBlockEntity.this.maxProgress;
                    case 2 -> PrimitiveWorkbenchBlockEntity.this.durability;
                    case 3 -> PrimitiveWorkbenchBlockEntity.this.maxDurability;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch(pIndex) {
                    case 0 -> PrimitiveWorkbenchBlockEntity.this.progress = pValue;
                    case 1 -> PrimitiveWorkbenchBlockEntity.this.maxProgress = pValue;
                    case 2 -> PrimitiveWorkbenchBlockEntity.this.durability = pValue;
                    case 3 -> PrimitiveWorkbenchBlockEntity.this.maxDurability = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.gtceu.primitive_workbench");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory inventory, Player player) {
        System.out.println("Menu creation requested");
        return new PrimitiveWorkbenchMenu(pContainerId, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("primitive_workbench.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("primitive_workbench.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
                craftItem();
                resetProgress();
                reduceDurability();
                increaseMileStoneCounterT0();
                if(durability <= 0) {
                    DestroyBlockEntity();
                }
            }
        } else {
            resetProgress();
        }
    }

    private void DestroyBlockEntity() {
    }

    private void increaseMileStoneCounterT0() {
    }

    private void reduceDurability() {
        durability = durability - 1;
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.WOODEN_WHEEL.get(), 1);
        this.itemHandler.extractItem(INPUT_SLOT1, 6, false);
        this.itemHandler.extractItem(INPUT_SLOT2, 6, false);
        this.itemHandler.extractItem(INPUT_SLOT3, 2, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT1, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT1).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        // 1. Check Flint Bolt (Use >= so it doesn't fail if they put more than 6)
        ItemStack slot1 = this.itemHandler.getStackInSlot(INPUT_SLOT1);
        boolean input1 = slot1.is(ModItems.FLINT_BOLT.get()) && slot1.getCount() >= 6;

        // 2. Check Planks Tag
        ItemStack slot2 = this.itemHandler.getStackInSlot(INPUT_SLOT2);
        boolean input2 = slot2.is(ItemTags.PLANKS) && slot2.getCount() >= 6;

        // 3. Check GTCEu Sticky Resin Tag
        ItemStack slot3 = this.itemHandler.getStackInSlot(INPUT_SLOT3);
        boolean input3 = slot3.getItem().toString().contains("sticky_resin") && slot3.getCount() >= 2;

        //System.out.println("1: " + input1 + " | 2: " + input2 + " | 3: " + input3); //check in logs
        boolean hasCraftingItem = input1 && input2 && input3;

        // 4. Output validation
        ItemStack result = new ItemStack(ModItems.WOODEN_WHEEL.get());

        return hasCraftingItem
                && canInsertAmountIntoOutputSlot(result.getCount())
                && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT1).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT1).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT1).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT1).getMaxStackSize();
    }
}
