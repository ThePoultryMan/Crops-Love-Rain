package io.github.thepoultryman.cropsloverain.mixin;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
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

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;isAreaLoaded(Lnet/minecraft/core/BlockPos;I)Z",
                    shift = At.Shift.AFTER
            ),
            method = "randomTick",
            cancellable = true
    )
    public void crops_love_rain$randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && CropsLoveRain.shouldGrowExtra(world, pos, random, CropsLoveRain.CropType.Sapling)) {
            advanceTree(world, pos, state, random);
            if (CropsLoveRain.CONFIG.debugMode.get()) {
                CropsLoveRain.LOGGER.info("{} grew into a tree", this);
            }
        }
        if (CropsLoveRain.CONFIG.debugMode.get() && CropsLoveRain.CONFIG.haltRegularGrowth.get()) {
            ci.cancel();
        }
    }
}
