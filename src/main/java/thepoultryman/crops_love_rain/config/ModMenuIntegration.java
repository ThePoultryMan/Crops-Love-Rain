package thepoultryman.crops_love_rain.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.thepoultryman.cactusconfig.screen.ConfigScreen;
import io.github.thepoultryman.cactusconfig.screen.ScreenCustomizer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import thepoultryman.crops_love_rain.CropsLoveRain;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        ScreenCustomizer screenCustomizer = new ScreenCustomizer(Text.translatable("crops_love_rain.config.title"));
        return (ConfigScreenFactory<Screen>) parent -> new ConfigScreen(
                screenCustomizer, parent, CropsLoveRain.CONFIG, CropsLoveRain.CONFIG.getOptionHolders()
        );
    }
}
