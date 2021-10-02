package top.hendrixshen.SyncmaticaPatches.mixin;

import ch.endte.syncmatica.Context;
import ch.endte.syncmatica.IFileStorage;
import ch.endte.syncmatica.SyncmaticManager;
import ch.endte.syncmatica.Syncmatica;
import ch.endte.syncmatica.communication.CommunicationManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.hendrixshen.SyncmaticaPatches.Config;

import java.io.File;

@Mixin(value = Syncmatica.class, remap = false)
public abstract class MixinSyncmatica {
    @Shadow
    @Final
    private static String SERVER_PATH;

    @Shadow
    private static void init(Context con, Identifier contextId) {
    }

    /**
     * @author Hendrix-Shen
     * @reason Modify server path
     */
    @Overwrite
    public static void initServer(final CommunicationManager comms, final IFileStorage fileStorage, final SyncmaticManager schematics) {
        File file = new File(Config.config.get("serverPath"));
        file = file.isDirectory() ? file : new File(SERVER_PATH);
        final Context serverContext = new Context(fileStorage, comms, schematics, true, file);
        init(serverContext, Syncmatica.SERVER_CONTEXT);
    }
}
