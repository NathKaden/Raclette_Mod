package yt.dasnilo.raclettemod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import yt.dasnilo.raclettemod.contents.RacletteBlockEntities;

public class TestBlockEntity extends BlockEntity{

    public TestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(RacletteBlockEntities.TEST_BLOCK.get(), pPos, pBlockState);
    }

}