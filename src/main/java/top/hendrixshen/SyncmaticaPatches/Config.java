package top.hendrixshen.SyncmaticaPatches;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Config {
    public static HashMap<String, String> config = new HashMap<String, String>();

    public static void saveConfig(File file) {
        config.clear();
        config.put("serverPath", "./syncmatics");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(gson.toJson(config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig(File file) {
        String dataJSON;
        try {
            dataJSON = IOUtils.toString(new FileInputStream(file), StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException e) {
            dataJSON = "{}";
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        config = gson.fromJson(dataJSON, new TypeToken<HashMap<String, String>>() {
        }.getType());
    }
}
