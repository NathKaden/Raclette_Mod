package yt.dasnilo.raclettemod.blocks;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;
import yt.dasnilo.raclettemod.recipe.TestBlockRecipe;

public class TestBlock extends BaseEntityBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public TestBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING,pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return super.mirror(p_54122_, p_54123_);
    }
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // ---------------------
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof TestBlockEntity testblockentity) {
           ItemStack itemstack = pPlayer.getItemInHand(pHand);
           Optional<TestBlockRecipe> optional = testblockentity.getCookableRecipe(itemstack);
           if (optional.isPresent()) {
              if (!pLevel.isClientSide && testblockentity.placeFood(pPlayer, pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().getCookingTime())) {
                 return InteractionResult.SUCCESS;
              }
  
              return InteractionResult.CONSUME;
           }
        }
  
        return InteractionResult.PASS;
     }

    
     public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
           BlockEntity blockentity = pLevel.getBlockEntity(pPos);
           if (blockentity instanceof TestBlockEntity) {
              Containers.dropContents(pLevel, pPos, ((TestBlockEntity)blockentity).getItems());
           }
  
           super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
     }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TestBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type){
        return true ? createTickerHelper(type, RacletteBlockEntities.TEST_BLOCK.get(), TestBlockEntity::cookTick) : createTickerHelper(type, RacletteBlockEntities.TEST_BLOCK.get(), TestBlockEntity::cooldownTick);
    }

}