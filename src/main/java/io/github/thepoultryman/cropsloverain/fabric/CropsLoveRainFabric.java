//? if fabric {
/*package io.github.thepoultryman.cropsloverain.fabric;

//? if >=1.21.5 {
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
//? } else
//import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class CropsLoveRainFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        //? if >=1.21.5 {
         ConfigRegistry.INSTANCE
        //? } else
        //NeoForgeConfigRegistry.INSTANCE
        .register(CropsLoveRain.MOD_ID, ModConfig.Type.SERVER, CropsLoveRainConfig.CONFIG_SPEC, "cropsloverain.toml");
    }
}
*///?}