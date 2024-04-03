package thepoultryman.crops_love_rain.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import thepoultryman.crops_love_rain.CropsLoveRain;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> CropsConfigManager.getScreen(parent, CropsLoveRain.MOD_ID);
    }
}
