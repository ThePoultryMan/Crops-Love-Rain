package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CocoaBlock.class)
public abstract class CocoaGrowthSpeedup {
    @Shadow @Final public static IntegerProperty AGE;

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    private void crops_love_rain$randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        int age = blockState.getValue(AGE);
        if (age < 2 && CropsLoveRain.shouldGrowExtra(serverLevel, blockPos, randomSource, CropsLoveRain.CropType.Cocoa)) {
            serverLevel.setBlock(blockPos, blockState.setValue(AGE, age + 1), 2);
            if (CropsLoveRainConfig.debugMode) {
                CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
            }
        }

        if (CropsLoveRainConfig.debugMode && CropsLoveRainConfig.haltRegularGrowth) {
            ci.cancel();
        }
    }
}
