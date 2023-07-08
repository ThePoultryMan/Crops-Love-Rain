package thepoultryman.crops_love_rain.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.crops_love_rain.CropsLoveRain;

@Mixin(SugarCaneBlock.class)
public class SugarCaneGrowthSpeedup extends Block {
    // DO NOT USE; FOR BLOCK STATE ONLY
    public SugarCaneGrowthSpeedup(Settings settings) throws Exception {
        super(settings);
        throw new Exception("This class should not be used. It is a mixin.");
    }

    @Shadow @Final public static IntProperty AGE;

    @Inject(at = @At("HEAD"), method = "randomTick")
    public void crops_love_rain$sugarCaneExtraTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        if (world.isAir(pos.up())) {
            int caneBlocks; // Determines how may sugar canes are in a "pillar".
            for (caneBlocks = 1; world.getBlockState(pos.down(caneBlocks)).isOf(Blocks.SUGAR_CANE); ++caneBlocks);

            if (caneBlocks < 3 && CropsLoveRain.shouldGrowExtra(world, pos, random, CropsLoveRain.CropType.SugarCane)) {
                int age = state.get(AGE);
                if (age == 15) {
                    world.setBlockState(pos.up(), this.getDefaultState());               // Creates a sugar cane block above this one.
                    world.setBlockState(pos, state.with(AGE, 0), Block.NO_REDRAW); // Sets this sugar cane's age to 0.
                } else {
                    world.setBlockState(pos, state.with(AGE, 15), Block.NO_REDRAW); // Skip to last age.
                }
            }
        }
    }
}
