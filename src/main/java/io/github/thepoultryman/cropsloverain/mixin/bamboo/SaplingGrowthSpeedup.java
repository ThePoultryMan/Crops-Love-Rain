package io.github.thepoultryman.cropsloverain.mixin.bamboo;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BambooSaplingBlock.class)
public abstract class SaplingGrowthSpeedup {
    @Shadow protected abstract void growBamboo(Level level, BlockPos pos);

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    public void crops_love_rain$extraSaplingTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (level.isEmptyBlock(pos.above()) && level.getRawBrightness(pos.above(), 0) >= 9) {
            this.growBamboo(level, pos);
            if (CropsLoveRain.CONFIG.debugMode.get()) {
                CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
            }
        }
        if (CropsLoveRain.CONFIG.debugMode.get() && CropsLoveRain.CONFIG.haltRegularGrowth.get()) {
            ci.cancel();
        }
    }
}
