package top.hendrixshen.SyncmaticaPatches;

import net.fabricmc.loader.api.FabricLoader;

public class Reference {
    public static final String MOD_ID = "syncmatica-patches";
    public static final String MOD_NAME = "Syncmatica Patches";
    public static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    public static final String MOD_VERSION_TYPE = "Version Exception";
}