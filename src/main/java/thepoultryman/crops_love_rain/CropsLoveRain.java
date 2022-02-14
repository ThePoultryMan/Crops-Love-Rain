package thepoultryman.crops_love_rain;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CropsLoveRain implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("crops_love_rain");

	public static final GameRules.Key<GameRules.IntRule> CROP_GROWTH_SPEED_DURING_RAIN = GameRuleRegistry.register("cropGrowthSpeedDuringRain", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(10));

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing rain onto crops");
	}
}
