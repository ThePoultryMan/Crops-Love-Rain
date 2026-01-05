package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
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

    @Shadow
    public abstract int getMaxAge();

    @Inject(
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/world/level/block/CropBlock;getAge(Lnet/minecraft/world/level/block/state/BlockState;)I"
            ),
            method = "randomTick",
            cancellable = true
    )
    public void crops_love_rain$randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        int currentAge = this.getAge(state);
        if (CropsLoveRain.shouldGrowExtra(level, pos, random, CropsLoveRain.CropType.Crop) && currentAge < this.getMaxAge()) {
            level.setBlock(pos, this.getStateForAge(currentAge + 1), 2);
            if (CropsLoveRain.CONFIG.debugMode.get()) {
                CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
            }
        }
        if (CropsLoveRain.CONFIG.debugMode.get() && CropsLoveRain.CONFIG.haltRegularGrowth.get()) {
            ci.cancel();
        }
    }
}