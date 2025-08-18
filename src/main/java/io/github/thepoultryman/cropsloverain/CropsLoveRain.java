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

    public static final CropsLoveRainConfig CONFIG = CropsLoveRainConfig.INSTANCE;


	public static boolean shouldGrowExtra(Level level, BlockPos blockPos, RandomSource random, CropType cropType) {
		if ((!level.isRainingAt(blockPos) && cropType != CropType.Bamboo) || !CONFIG.useRainGrowthSpeed.get()) {
			return false;
		} else if (cropType == CropType.Bamboo && !level.isRaining()) {
			return false;
		}
		int growthSpeed = switch (cropType) {
			case Bamboo -> CONFIG.bambooCustomSpeed.get();
			case Cocoa -> CONFIG.cocoaCustomSpeed.get();
			case Crop -> CONFIG.cropsCustomSpeed.get();
			case Sapling -> CONFIG.saplingCustomSpeed.get();
			case SugarCane -> CONFIG.sugarCaneCustomSpeed.get();
			case SweetBerries -> CONFIG.sweetBerryCustomSpeed.get();
			case Melon -> CONFIG.melonCustomSpeed.get();
			case Pumpkin -> CONFIG.pumpkinCustomSpeed.get();
		};
		if (growthSpeed == 0) {
			return false;
		}
		if (CONFIG.useIndividualSpeeds.get()) {

			return random.nextInt(growthSpeed) == 0;
		} else {
			return random.nextInt(CONFIG.rainGrowthSpeed.get()) == 0;
		}
	}

	public enum CropType {
		Bamboo,
		Cocoa,
		Crop,
		Sapling,
		SugarCane,
		SweetBerries,
		Melon,
		Pumpkin
	}
}
