package io.github.thepoultryman.cropsloverain.mixin.bamboo;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import net.minecraft.block.BambooSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BambooSaplingBlock.class)
public abstract class SaplingGrowthSpeedup {
    @Shadow protected abstract void grow(World world, BlockPos pos);

    @Inject(at = @At("TAIL"), method = "randomTick")
    public void crops_love_rain$extraSaplingTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        if (world.isAir(pos.up()) && world.getBaseLightLevel(pos.up(), 0) >= 9 && CropsLoveRain.shouldGrowExtra(world, pos, random, CropsLoveRain.CropType.Bamboo)) {
            this.grow(world, pos);
        }
    }
}
