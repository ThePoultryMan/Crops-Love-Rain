package thepoultryman.crops_love_rain.config;

import io.github.thepoultryman.cactusconfig.ConfigManager;
import io.github.thepoultryman.cactusconfig.OptionHolder;
import io.github.thepoultryman.cactusconfig.Options;
import net.minecraft.text.Text;

public class CropsConfigManager extends ConfigManager {
    @Options.OptionHolder
    public OptionHolder general = new OptionHolder(Text.translatable("crops_love_rain.config.general"), null);
    @Options.Boolean(tab = "general")
    public boolean useRainGrowthSpeed;
    @Options.Integer(tab = "general", defaultValue = 10)
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
