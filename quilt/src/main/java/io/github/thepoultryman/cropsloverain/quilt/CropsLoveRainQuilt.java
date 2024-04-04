package io.github.thepoultryman.cropsloverain.quilt;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class CropsLoveRainQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        CropsLoveRain.init();
    }
}
