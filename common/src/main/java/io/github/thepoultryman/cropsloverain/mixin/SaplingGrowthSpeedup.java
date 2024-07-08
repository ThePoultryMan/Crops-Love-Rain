package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaplingBlock.class)
public abstract class SaplingGrowthSpeedup {
    @Shadow public abstract void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random);

    @Inject(at = @At("TAIL"), method = "randomTick")
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (CropsLoveRain.shouldGrowExtra(world, pos, random, CropsLoveRain.CropType.Sapling)) {
            advanceTree(world, pos, state, random);
            if (CropsLoveRainConfig.debugMode) {
                CropsLoveRain.LOGGER.info(this + " grew into a tree");
            }
        }
    }
}
