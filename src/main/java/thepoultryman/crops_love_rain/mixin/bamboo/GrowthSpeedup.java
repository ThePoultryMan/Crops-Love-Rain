package thepoultryman.crops_love_rain.mixin.bamboo;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.crops_love_rain.CropsLoveRain;

@Mixin(BambooBlock.class)
public abstract class GrowthSpeedup {
    @Shadow @Final public static IntProperty STAGE;

    @Shadow protected abstract int countBambooBelow(BlockView world, BlockPos pos);

    @Shadow protected abstract void updateLeaves(BlockState state, World world, BlockPos pos, RandomGenerator random, int height);

    @Inject(at = @At("HEAD"), method = "randomTick")
    public void crops_love_rain$extraBlockTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        if (!CropsLoveRain.CONFIG.useRainGrowthSpeed || !world.hasRain(pos)) return;
        if (state.get(STAGE) == 0 && world.isAir(pos.up()) && world.getBaseLightLevel(pos.up(), 0) >= 9 && CropsLoveRain.shouldGrowExtra(world, random)) {
            int bambooBelow = countBambooBelow(world, pos) + 1;
            if (bambooBelow > 16) {
                this.updateLeaves(state, world, pos, random, bambooBelow);
            }
        }
    }
}
