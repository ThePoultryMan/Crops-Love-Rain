package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SweetBerryBushBlock.class)
public class BerryBushGrowthSpeedup {
    @Shadow @Final public static IntegerProperty AGE;

    @Inject(at = @At("HEAD"), method = "randomTick", cancellable = true)
    private void crops_love_rain$randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        int i = blockState.getValue(AGE);
        if (i < 3 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9 && CropsLoveRain.shouldGrowExtra(serverLevel, blockPos, randomSource, CropsLoveRain.CropType.SweetBerries)) {
            BlockState newBlockState = blockState.setValue(AGE, i + 1);
            serverLevel.setBlock(blockPos, newBlockState, 2);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(newBlockState));
        }
        if (CropsLoveRainConfig.debugMode && CropsLoveRainConfig.haltRegularGrowth) {
            ci.cancel();
        }
    }
}
