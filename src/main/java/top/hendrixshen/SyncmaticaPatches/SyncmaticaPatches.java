package top.hendrixshen.SyncmaticaPatches;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class SyncmaticaPatches implements ModInitializer {
    public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

    @Override
    public void onInitialize() {
        File configFolder = new File(new File("."), String.format("config/%s", Reference.MOD_ID));
        File configFile = new File(configFolder, "config.json");
        if (configFile.exists()) {
            Config.loadConfig(configFile);
        } else {
            configFolder.mkdirs();
            try {
                configFile.createNewFile();
                Config.saveConfig(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info(String.format("[%s]: Mod initialized - Version: %s (%s)", Reference.MOD_NAME, Reference.MOD_VERSION, Reference.MOD_VERSION_TYPE));

    }
}
