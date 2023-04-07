package yt.dasnilo.raclettemod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

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
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TestBlockEntity(pPos, pState);
    }

}