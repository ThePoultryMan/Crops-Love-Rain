package thepoultryman.crops_love_rain;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import thepoultryman.crops_love_rain.config.CropsConfigManager;

public class CropsLoveRain implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("crops_love_rain");

	public static final CropsConfigManager CONFIG = new CropsConfigManager("crops-love-rain", true);

	@Override
	public void onInitialize() {
		CONFIG.loadConfig();
	}

	public static boolean shouldGrowExtra(World world, BlockPos blockPos, RandomGenerator random, CropType cropType) {
		if (!world.hasRain(blockPos) || !CONFIG.useRainGrowthSpeed) return false;
		int growthSpeed = 0;
		if (CONFIG.useIndividualSpeeds) {
			if (CONFIG.usesCustomSpeed(cropType)) {
				growthSpeed = switch (cropType) {
					case Bamboo -> CONFIG.bambooCustomSpeed;
					case Crop -> CONFIG.cropsCustomSpeed;
					case Sapling -> CONFIG.saplingCustomSpeed;
					case SugarCane -> CONFIG.sugarCaneCustomSpeed;
				};
				return random.nextInt(growthSpeed) == 0;
			} else {
				return random.nextInt(CONFIG.rainGrowthSpeed) == 0;
			}
		} else {
			return random.nextInt(CONFIG.rainGrowthSpeed) == 0;
		}
	}

	public enum CropType {
		Bamboo,
		Crop,
		Sapling,
		SugarCane,
	}
}
