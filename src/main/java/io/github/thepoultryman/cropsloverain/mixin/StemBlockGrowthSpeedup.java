package io.github.thepoultryman.cropsloverain.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StemBlock.class)
public abstract class StemBlockGrowthSpeedup extends VegetationBlock {
    @Unique
    private int crops_Love_Rain$extraGrowth;
    @Unique
    private static final ResourceLocation MELON_LOCATION = ResourceLocation.withDefaultNamespace("melon_stem");

    protected StemBlockGrowthSpeedup(Properties properties) {
        super(properties);
        throw new AssertionError("This constructor should not be called.");
    }

    @Inject(at = @At("HEAD"), method = "randomTick")
    private void crops_love_rain$randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        CropsLoveRain.CropType cropType;
        if (CropsLoveRainConfig.separateStemSpeed) {
            cropType = blockState.getBlockHolder().is(MELON_LOCATION) ? CropsLoveRain.CropType.Melon : CropsLoveRain.CropType.Pumpkin;
        } else {
            cropType = CropsLoveRain.CropType.Crop;
        }
        if (CropsLoveRain.shouldGrowExtra(serverLevel, blockPos, randomSource, cropType)) {
            crops_Love_Rain$extraGrowth += 1;
        }
    }

    @Debug(export = true)
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"), method = "randomTick")
    private int crops_love_rain$randomTickCheck(int original) {
        boolean haltGrowth = CropsLoveRainConfig.debugMode && CropsLoveRainConfig.haltRegularGrowth;
        if (haltGrowth) {
            if (crops_Love_Rain$extraGrowth > 0) {
                crops_Love_Rain$extraGrowth -= 1;
                CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
                return 0;
            } else {
                return 1;
            }
        } else {
            if (original == 0) {
                return original;
            } else if (crops_Love_Rain$extraGrowth > 0) {
                crops_Love_Rain$extraGrowth -= 1;
                CropsLoveRain.LOGGER.info("{} grew an extra state.", this);
                return 0;
            } else {
                return original;
            }
        }
    }
}
