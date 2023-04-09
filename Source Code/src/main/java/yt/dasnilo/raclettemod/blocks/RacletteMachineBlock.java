package yt.dasnilo.raclettemod.blocks;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;
import yt.dasnilo.raclettemod.recipe.RacletteRecipe;

public class RacletteMachineBlock extends BaseEntityBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_WE = Block.box(2, 0, 1, 14, 8, 15);
    private static final VoxelShape SHAPE_NS = Block.box(1,0,2,15,8,14);

    public RacletteMachineBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collision) {
        Direction direction = state.getValue(FACING);
        return direction.getAxis() == Direction.Axis.Z ? SHAPE_NS : SHAPE_WE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }
    
    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
    // ---------------------------------------------------------------------
    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
 
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof RacletteMachineBlockEntity raclettemachineblockentity) {
           ItemStack itemstack = pPlayer.getItemInHand(pHand);
           Optional<RacletteRecipe> optional = raclettemachineblockentity.getCookableRecipe(itemstack);
           if (optional.isPresent()) {
              if (!pLevel.isClientSide && raclettemachineblockentity.placeFood(pPlayer, pPlayer.getAbilities().instabuild ? itemstack.copy() : itemstack, optional.get().getCookingTime())) {
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
           if (blockentity instanceof RacletteMachineBlockEntity) {
              Containers.dropContents(pLevel, pPos, ((RacletteMachineBlockEntity)blockentity).getItems());
           }
  
           super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new RacletteMachineBlockEntity(pPos, pState);
    }

    public static void dowse(@Nullable Entity entity, LevelAccessor level, BlockPos pos, BlockState state){
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof RacletteMachineBlockEntity){
            ((RacletteMachineBlockEntity)blockEntity).dowse();
        }
        level.gameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, RacletteBlockEntities.RACLETTE_MACHINE.get(), RacletteMachineBlockEntity::cookTick);
    }

}