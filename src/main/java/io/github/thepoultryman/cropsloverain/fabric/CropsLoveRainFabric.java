//? if fabric {
package io.github.thepoultryman.cropsloverain.fabric;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import net.fabricmc.api.ModInitializer;

public class CropsLoveRainFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CropsLoveRain.init();
    }
}
//?}