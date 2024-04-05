package io.github.thepoultryman.cropsloverain;

import io.github.thepoultryman.cropsloverain.config.CropsLoveRainConfig;

public class CropsLoveRain {
	public static final String MOD_ID = "cropsloverain";

	public static void init() {
		CropsLoveRainConfig.init(MOD_ID, CropsLoveRainConfig.class);
	}
}
