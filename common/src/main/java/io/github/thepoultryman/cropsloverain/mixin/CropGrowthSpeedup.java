package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CropBlock.class)
public abstract class CropGrowthSpeedup {
    @Shadow public abstract BlockState getStateForAge(int age);

    @Shadow
    public abstract int getAge(BlockState state);

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    public void crops_love_rain$randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            if (this.getAge(state) < 7 && CropsLoveRain.shouldGrowExtra(level, pos, random, CropsLoveRain.CropType.Crop)) {
                level.setBlock(pos, this.getStateForAge(this.getAge(state) + 1), 2);
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