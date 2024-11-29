package io.github.thepoultryman.cropsloverain.mixin.bamboo;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BambooStalkBlock.class)
public abstract class GrowthSpeedup {
    @Shadow @Final public static IntegerProperty STAGE;

    @Shadow protected abstract int getHeightBelowUpToMax(BlockGetter world, BlockPos pos);

    @Shadow protected abstract void growBamboo(BlockState state, Level level, BlockPos pos, RandomSource random, int height);

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    public void crops_love_rain$extraBlockTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (state.getValue(STAGE) == 0 && level.getBlockState(pos.above()).isAir() && level.getRawBrightness(pos.above(), 0) >= 9 && CropsLoveRain.shouldGrowExtra(level, pos, random, CropsLoveRain.CropType.Bamboo)) {
            int bambooBelow = getHeightBelowUpToMax(level, pos) + 1;
            if (bambooBelow < 16) {
                this.growBamboo(state, level, pos, random, bambooBelow);
                if (CropsLoveRainConfig.debugMode) {
                    CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
                }
            }
        }
        if (CropsLoveRainConfig.debugMode && CropsLoveRainConfig.haltRegularGrowth) {
            ci.cancel();
        }
    }
}
