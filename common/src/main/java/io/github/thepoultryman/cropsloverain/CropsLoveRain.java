package io.github.thepoultryman.cropsloverain;

import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CropsLoveRain {
	public static final String MOD_ID = "cropsloverain";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void init() {
		CropsLoveRainConfig.init(MOD_ID, CropsLoveRainConfig.class);
	}

	public static boolean shouldGrowExtra(Level level, BlockPos blockPos, RandomSource random, CropType cropType) {
		if ((!level.isRainingAt(blockPos) && cropType != CropType.Bamboo) || !CropsLoveRainConfig.useRainGrowthSpeed) {
			return false;
		} else if (cropType == CropType.Bamboo && !level.isRaining()) {
			return false;
		}
		int growthSpeed = switch (cropType) {
			case Bamboo -> CropsLoveRainConfig.bambooCustomSpeed;
			case Cocoa -> CropsLoveRainConfig.cocoaCustomSpeed;
			case Crop -> CropsLoveRainConfig.cropsCustomSpeed;
			case Sapling -> CropsLoveRainConfig.saplingCustomSpeed;
			case SugarCane -> CropsLoveRainConfig.sugarCaneCustomSpeed;
			case SweetBerries -> CropsLoveRainConfig.sweetBerryCustomSpeed;
		};
		if (growthSpeed == 0) {
			return false;
		}
		if (CropsLoveRainConfig.useIndividualSpeeds) {

			return random.nextInt(growthSpeed) == 0;
		} else {
			return random.nextInt(CropsLoveRainConfig.rainGrowthSpeed) == 0;
		}
	}

	public enum CropType {
		Bamboo,
		Cocoa,
		Crop,
		Sapling,
		SugarCane,
		SweetBerries,
	}
}
