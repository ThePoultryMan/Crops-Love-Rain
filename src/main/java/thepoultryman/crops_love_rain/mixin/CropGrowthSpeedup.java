package thepoultryman.crops_love_rain.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thepoultryman.crops_love_rain.CropsLoveRain;

import java.util.Random;

@Mixin(CropBlock.class)
public abstract class CropGrowthSpeedup {
	@Shadow public abstract BlockState withAge(int age);

	@Shadow protected abstract int getAge(BlockState state);

	@Shadow
	protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
		return 0;
	}

	@Inject(at = @At("HEAD"), method = "randomTick")
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
		if (world.getBaseLightLevel(pos, 0) >= 9 && world.isRaining()) {
			Block cropBlock = world.getBlockState(pos).getBlock();
			int rainGrowthSpeed = world.getGameRules().getInt(CropsLoveRain.CROP_GROWTH_SPEED_DURING_RAIN);
			if (random.nextInt((int) (rainGrowthSpeed / getAvailableMoisture(cropBlock, world, pos)) + 1) == 0) {
				world.setBlockState(pos, this.withAge(this.getAge(state) + 1), 2);
			}
		}
	}

}
