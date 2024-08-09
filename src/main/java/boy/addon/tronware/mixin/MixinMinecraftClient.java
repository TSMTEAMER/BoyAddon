package boy.addon.tronware.mixin;

import boy.addon.tronware.TronWare;
import dev.boze.api.BozeInstance;
import dev.boze.api.exception.AddonInitializationException;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(RunArgs args, CallbackInfo ci) {
        try {
            BozeInstance.INSTANCE.registerAddon(TronWare.INSTANCE);
        } catch (AddonInitializationException e) {
            TronWare.LOG.fatal("Failed to initialize " + TronWare.INSTANCE.id, e);
        }
    }
}
