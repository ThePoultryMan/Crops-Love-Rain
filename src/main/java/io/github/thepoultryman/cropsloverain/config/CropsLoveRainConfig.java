package io.github.thepoultryman.cropsloverain.config;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.ApiStatus;

public class CropsLoveRainConfig {
    @ApiStatus.Internal
    public static final CropsLoveRainConfig INSTANCE;
    public static final ModConfigSpec CONFIG_SPEC;

    public ModConfigSpec.ConfigValue<Boolean> useRainGrowthSpeed;
//    public static Comment chanceDescription;
    public ModConfigSpec.ConfigValue<Integer> rainGrowthSpeed;

    public ModConfigSpec.ConfigValue<Boolean> useIndividualSpeeds;
//    public static Comment chanceDescriptionIndividual;
    public ModConfigSpec.ConfigValue<Integer> bambooCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> cocoaCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> cropsCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> saplingCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> sugarCaneCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> sweetBerryCustomSpeed;
//    public static Comment stemSpeedSection;
    public ModConfigSpec.ConfigValue<Boolean> separateStemSpeed;
    public ModConfigSpec.ConfigValue<Integer> melonCustomSpeed;
    public ModConfigSpec.ConfigValue<Integer> pumpkinCustomSpeed;

    public ModConfigSpec.ConfigValue<Boolean> debugMode;
    public ModConfigSpec.ConfigValue<Boolean> haltRegularGrowth;

    public CropsLoveRainConfig(ModConfigSpec.Builder builder) {
        this.useRainGrowthSpeed = builder.define("general.use_rain_growth_speed", true);
        this.rainGrowthSpeed = builder
                .comment(Component.translatable("cropsloverain.configuration.chance_description").getString())
                .define("general.rain_growth_speed", 10);

        this.useIndividualSpeeds = builder
                .comment(Component.translatable("cropsloverain.configuration.chance_description_individual").getString())
                .define("individual.use_individual_speeds", false);
        this.bambooCustomSpeed = builder.define("individual.bamboo_custom_speed", 10);
        this.cocoaCustomSpeed = builder.define("individual.cocoa_custom_speed", 10);
        this.cropsCustomSpeed = builder.define("individual.crops_custom_speed", 10);
        this.saplingCustomSpeed = builder.define("individual.sapling_custom_speed", 10);
        this.sugarCaneCustomSpeed = builder.define("individual.sugar_cane_custom_speed", 10);
        this.sweetBerryCustomSpeed = builder.define("individual.sweet_berry_custom_speed", 10);
        this.separateStemSpeed = builder.define("individual.separate_stem_speed", false);
        this.melonCustomSpeed = builder.define("individual.melon_custom_speed", 10);
        this.pumpkinCustomSpeed = builder.define("individual.pumpkin_custom_speed", 10);

        this.debugMode = builder.define("debug.debug_mode", false);
        this.haltRegularGrowth = builder.define("debug.halt_regular_growth", false);
    }

    static {
        Pair<CropsLoveRainConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(CropsLoveRainConfig::new);

        INSTANCE = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
