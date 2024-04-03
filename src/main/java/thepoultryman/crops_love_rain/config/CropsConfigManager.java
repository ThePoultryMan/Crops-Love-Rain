package thepoultryman.crops_love_rain.config;

import eu.midnightdust.lib.config.MidnightConfig;
import thepoultryman.crops_love_rain.CropsLoveRain;

public class CropsConfigManager extends MidnightConfig {
    @Entry(category = "general")
    public static boolean useRainGrowthSpeed = true;
    @Entry(category = "general")
    public static int rainGrowthSpeed = 10;

    @Entry(category = "individual")
    public static boolean useIndividualSpeeds = false;

    @Entry(category = "individual")
    public static boolean useGeneralSpeedBamboo = true;
    @Entry(category = "individual")
    public static int bambooCustomSpeed = 10;
    @Entry(category = "individual")
    public static boolean useGeneralSpeedCrops = true;
    @Entry(category = "individual")
    public static int cropsCustomSpeed = 10;
    @Entry(category = "individual")
    public static boolean useGeneralSpeedSaplings = true;
    @Entry(category = "individual")
    public static int saplingCustomSpeed = 10;
    @Entry(category = "individual")
    public static boolean useGeneralSpeedSugarCane = true;
    @Entry(category = "individual")
    public static int sugarCaneCustomSpeed = 10;

    public static boolean usesCustomSpeed(CropsLoveRain.CropType cropType) {
        return switch (cropType) {
            case Bamboo -> useGeneralSpeedBamboo;
            case Crop -> useGeneralSpeedCrops;
            case Sapling -> useGeneralSpeedSaplings;
            case SugarCane -> useGeneralSpeedSugarCane;
        };
    }
}
