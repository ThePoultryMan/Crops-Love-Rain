package thepoultryman.crops_love_rain.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.text.Text;

public class CropsConfigManager extends MidnightConfig {
    @Entry(category = "general")
    public static boolean useRainGrowthSpeed = true;
    @Comment(category = "general")
    public static Comment chanceDescription;
    @Entry(category = "general", min = 1)
    public static int rainGrowthSpeed = 10;

    @Entry(category = "individual")
    public static boolean useIndividualSpeeds = false;
    @Comment(category = "individual")
    public static Comment chanceDescriptionIndividual;
    @Entry(category = "individual", min = 1)
    public static int bambooCustomSpeed = 10;
    @Entry(category = "individual", min = 1)
    public static int cropsCustomSpeed = 10;
    @Entry(category = "individual", min = 1)
    public static int saplingCustomSpeed = 10;
    @Entry(category = "individual", min = 1)
    public static int sugarCaneCustomSpeed = 10;
}
