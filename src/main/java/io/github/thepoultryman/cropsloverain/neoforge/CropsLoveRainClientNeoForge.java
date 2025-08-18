//? if neoforge {
package io.github.thepoultryman.cropsloverain.neoforge;

import io.github.thepoultryman.cropsloverain.CropsLoveRain;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CropsLoveRain.MOD_ID, dist = Dist.CLIENT)
public class CropsLoveRainClientNeoForge {
    public CropsLoveRainClientNeoForge(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
//?}
