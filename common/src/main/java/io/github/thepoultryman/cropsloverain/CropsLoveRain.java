package io.github.thepoultryman.cropsloverain;

import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;

public class CropsLoveRain {
	public static final String MOD_ID = "cropsloverain";

	public static void init() {
		CropsLoveRainConfig.init(MOD_ID, CropsLoveRainConfig.class);
	}

	public static boolean shouldGrowExtra(World world, BlockPos blockPos, RandomGenerator random, CropType cropType) {
		if (!world.hasRain(blockPos) || !CropsLoveRainConfig.useRainGrowthSpeed) return false;
		System.out.println(cropType.toString());
		if (CropsLoveRainConfig.useIndividualSpeeds) {
			int growthSpeed = switch (cropType) {
				case Bamboo -> CropsLoveRainConfig.bambooCustomSpeed;
				case Crop -> CropsLoveRainConfig.cropsCustomSpeed;
				case Sapling -> CropsLoveRainConfig.saplingCustomSpeed;
				case SugarCane -> CropsLoveRainConfig.sugarCaneCustomSpeed;
			};
			return random.nextInt(growthSpeed) == 0;
		} else {
			return random.nextInt(CropsLoveRainConfig.rainGrowthSpeed) == 0;
		}
	}

	public enum CropType {
		Bamboo,
		Crop,
		Sapling,
		SugarCane,
	}
}
