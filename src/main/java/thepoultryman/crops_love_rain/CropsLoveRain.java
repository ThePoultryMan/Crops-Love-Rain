package thepoultryman.crops_love_rain;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import thepoultryman.crops_love_rain.config.CropsConfigManager;

public class CropsLoveRain implements ModInitializer {
	public static final String MOD_ID = "crops_love_rain";

	@Override
	public void onInitialize() {
		CropsConfigManager.init(MOD_ID, CropsConfigManager.class);
	}

	public static boolean shouldGrowExtra(World world, BlockPos blockPos, RandomGenerator random, CropType cropType) {
		if (!world.hasRain(blockPos) || !CropsConfigManager.useRainGrowthSpeed) return false;
		if (CropsConfigManager.useIndividualSpeeds) {
			int growthSpeed = switch (cropType) {
				case Bamboo -> CropsConfigManager.bambooCustomSpeed;
				case Crop -> CropsConfigManager.cropsCustomSpeed;
				case Sapling -> CropsConfigManager.saplingCustomSpeed;
				case SugarCane -> CropsConfigManager.sugarCaneCustomSpeed;
			};
			return random.nextInt(growthSpeed) == 0;
		} else {
			return random.nextInt(CropsConfigManager.rainGrowthSpeed) == 0;
		}
	}

	public enum CropType {
		Bamboo,
		Crop,
		Sapling,
		SugarCane,
	}
}
