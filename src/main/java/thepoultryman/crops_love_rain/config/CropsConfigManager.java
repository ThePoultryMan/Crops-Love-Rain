package thepoultryman.crops_love_rain.config;

import io.github.thepoultryman.cactusconfig.ConfigManager;
import io.github.thepoultryman.cactusconfig.OptionHolder;
import io.github.thepoultryman.cactusconfig.Options;
import io.github.thepoultryman.cactusconfig.util.CactusUtil;
import net.minecraft.text.Text;

public class CropsConfigManager extends ConfigManager {
    @Options.OptionHolder
    public OptionHolder general = new OptionHolder(Text.translatable("crops_love_rain.config.general"), null);
    @Options.Boolean(tab = "general")
    public boolean useRainGrowthSpeed;
    @Options.Integer(tab = "general", defaultValue = 10)
    public int rainGrowthSpeed;
    @Options.OptionHolder
    public final OptionHolder individual = new OptionHolder(
            Text.translatable("crops_love_rain.config.individual"), Text.translatable("crops_love_rain.config.individual.desc")
    );
    @Options.Boolean(tab = "individual", defaultValue = false)
    public boolean useIndividualSpeeds;
    @Options.Separator(tab = "individual")
    private final CactusUtil.ConfigOption individualSeparator = new CactusUtil.ConfigOption(true, false);
    @Options.Boolean(tab = "individual")
    public boolean useGeneralSpeedBamboo;
    @Options.Integer(tab = "individual")
    public int bambooCustomSpeed;
    @Options.Boolean(tab = "individual")
    public boolean useGeneralSpeedCrops;
    @Options.Integer(tab = "individual")
    public int cropsCustomSpeed;
    @Options.Boolean(tab = "individual")
    public boolean useGeneralSpeedSaplings;
    @Options.Integer(tab = "individual")
    public int saplingCustomSpeed;
    @Options.Boolean(tab = "individual")
    public boolean useGeneralSpeedSugarCane;
    @Options.Integer(tab = "individual")
    public int sugarCaneCustomSpeed;

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
