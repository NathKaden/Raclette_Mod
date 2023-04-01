package yt.dasnilo.raclettemod.blocks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import yt.dasnilo.raclettemod.recipe.RacletteMachineRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;
import yt.dasnilo.raclettemod.screen.RacletteMachineMenu;
import java.util.Optional;

public class RacletteMachineBlockEntity extends BlockEntity implements MenuProvider{

    private final ItemStackHandler itemHandler = new ItemStackHandler(3){
        protected void onContentsChanged(int slot) {setChanged();};
    };
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    public RacletteMachineBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(RacletteBlockEntities.RACLETTE_MACHINE.get(), p_155229_, p_155230_);
        this.data = new ContainerData(){
            public int get(int p_39284_){
                return switch (p_39284_){
                    case 0 -> RacletteMachineBlockEntity.this.progress;
                    case 1 -> RacletteMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            public void set(int p_39285_, int p_39286_){
                switch (p_39285_){
                    case 0 -> RacletteMachineBlockEntity.this.progress = p_39286_;
                    case 1 -> RacletteMachineBlockEntity.this.maxProgress = p_39286_;
                };
            }
            public int getCount() {return 2;};
        };
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new RacletteMachineMenu(id, inv, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Raclette Machine");
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
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

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        p_187471_.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(p_187471_);
    }

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        itemHandler.deserializeNBT(p_155245_.getCompound("inventory"));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0;i<itemHandler.getSlots();i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RacletteMachineBlockEntity entity){
        if(level.isClientSide()){
            return;
        }

        if(hasRecipe(entity)){
            entity.progress++;
            setChanged(level, pos, state);

            if(entity.progress >= entity.maxProgress){
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private static void craftItem(RacletteMachineBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<RacletteMachineRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(RacletteMachineRecipe.Type.INSTANCE, inventory, level);
        
        if(hasRecipe(entity)){
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(), entity.itemHandler.getStackInSlot(2).getCount() + 1));
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(RacletteMachineBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for(int i = 0;i<entity.itemHandler.getSlots();i++){
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        Optional<RacletteMachineRecipe> recipe = level.getRecipeManager().getRecipeFor(RacletteMachineRecipe.Type.INSTANCE, inventory, level);
        return recipe.isPresent() && canInsertAmount(inventory) && canInsertItem(inventory, recipe.get().getResultItem());
    }

    private static boolean canInsertItem(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmount(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
