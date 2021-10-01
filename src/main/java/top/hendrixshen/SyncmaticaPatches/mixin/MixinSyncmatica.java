package top.hendrixshen.SyncmaticaPatches.mixin;

import ch.endte.syncmatica.Context;
import ch.endte.syncmatica.Syncmatica;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.hendrixshen.SyncmaticaPatches.Config;
import top.hendrixshen.SyncmaticaPatches.SyncmaticaPatches;

import java.io.File;

@Mixin(value = Syncmatica.class, remap = false)
public abstract class MixinSyncmatica {
    @Shadow
    private static void init(Context con, Identifier contextId) {
    }

    @Shadow @Final private static String SERVER_PATH;

    @Redirect(
            method = "initServer",
            at = @At(
                    value = "NEW"
            )
    )
    private static File redirectServerDataPath() {
        File file = new File(Config.config.get("serverPath"));
        if (file.exists()) {
            return file;
        }
        SyncmaticaPatches.logger.error(String.format("File not found: %s", file));
        return new File(SERVER_PATH);
    }
}
