package thepoultryman.crops_love_rain.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.crops_love_rain.CropsLoveRain;

import java.util.Random;

@Mixin(SaplingBlock.class)
public abstract class SaplingGrowthSpeedup {

    @Shadow public abstract void generate(ServerWorld world, BlockPos pos, BlockState state, Random random);

    @Inject(at = @At("TAIL"), method = "randomTick")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        int rainGrowthSpeed = world.getGameRules().getInt(CropsLoveRain.CROP_GROWTH_SPEED_DURING_RAIN);
        if (rainGrowthSpeed != 0 && world.hasRain(pos) && random.nextInt(rainGrowthSpeed / 2) == 0) {
            generate(world, pos, state, random);
        }
    }
}
