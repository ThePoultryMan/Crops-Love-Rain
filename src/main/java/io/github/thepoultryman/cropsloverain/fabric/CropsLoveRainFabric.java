//? if fabric {
/*package io.github.thepoultryman.cropsloverain.fabric;

import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class CropsLoveRainFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(CropsLoveRain.MOD_ID, ModConfig.Type.SERVER, CropsLoveRainConfig.CONFIG_SPEC, "cropsloverain.toml");
    }
}
*///?}