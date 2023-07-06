package thepoultryman.crops_love_rain;

import net.fabricmc.api.ModInitializer;
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

	public static boolean shouldGrowExtra(World world, RandomGenerator random) {
		return random.nextInt((CONFIG.rainGrowthSpeed * 10) / 2) == 0;
	}
}
