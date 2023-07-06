package thepoultryman.crops_love_rain.config;

import io.github.thepoultryman.cactusconfig.ConfigManager;
import io.github.thepoultryman.cactusconfig.OptionHolder;
import io.github.thepoultryman.cactusconfig.Options;
import net.minecraft.text.Text;
import thepoultryman.crops_love_rain.CropsLoveRain;

import java.util.Arrays;

public class CropsConfigManager extends ConfigManager {
    @Options.OptionHolder
    public OptionHolder speed = new OptionHolder(Text.translatable("crops_love_rain.config.speed"), Text.translatable("crops_love_rain.config.speed.desc"));
    @Options.Boolean(tab = "speed")
    public boolean useRainGrowthSpeed;
    @Options.Integer(tab = "speed", defaultValue = 10)
    public int rainGrowthSpeed;


    @Override
    protected void load() {
        super.load();
    }

    public CropsConfigManager(String fileName, boolean loadOnServer) {
        super(fileName, loadOnServer);
    }

    @Override
    public boolean canReset() {
        return true;
    }
}
