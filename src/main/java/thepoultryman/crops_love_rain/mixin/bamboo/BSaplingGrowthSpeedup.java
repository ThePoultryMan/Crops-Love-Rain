package thepoultryman.crops_love_rain.mixin.bamboo;

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
import thepoultryman.crops_love_rain.CropsLoveRain;

@Mixin(BambooSaplingBlock.class)
public abstract class BSaplingGrowthSpeedup {
    @Shadow protected abstract void grow(World world, BlockPos pos);

    @Inject(at = @At("TAIL"), method = "randomTick")
    public void crops_love_rain$extraSaplingTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        if (!CropsLoveRain.CONFIG.useRainGrowthSpeed || !world.hasRain(pos)) return;
        if (world.isAir(pos.up()) && world.getBaseLightLevel(pos.up(), 0) >= 9 && CropsLoveRain.shouldGrowExtra(world, random)) {
            this.grow(world, pos);
        }
    }
}
