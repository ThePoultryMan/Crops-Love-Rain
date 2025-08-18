//? if neoforge {
package io.github.thepoultryman.cropsloverain.neoforge;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(CropsLoveRain.MOD_ID)
public class CropsLoveRainNeoForge {
    public CropsLoveRainNeoForge(ModContainer container) {
        container.registerConfig(ModConfig.Type.SERVER, CropsLoveRainConfig.CONFIG_SPEC, "cropsloverain.toml");
    }
}
//?}
