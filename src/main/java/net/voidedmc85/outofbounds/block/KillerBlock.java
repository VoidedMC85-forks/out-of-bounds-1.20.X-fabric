package net.voidedmc85.outofbounds.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KillerBlock extends Block {

    public KillerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient) {
            entity.kill(); // Instantly kills the entity that steps on it
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
