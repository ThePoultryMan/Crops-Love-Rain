package thepoultryman.crops_love_rain.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.crops_love_rain.CropsLoveRain;

import java.util.Random;

@Mixin(SaplingBlock.class)
public abstract class SaplingGrowthSpeedup {
    @Shadow public abstract void generate(ServerWorld world, BlockPos pos, BlockState state, RandomGenerator random);

    @Inject(at = @At("TAIL"), method = "randomTick")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        if (CropsLoveRain.shouldGrowExtra(world, pos, random, CropsLoveRain.CropType.Sapling)) {
            generate(world, pos, state, random);
        }
    }
}
