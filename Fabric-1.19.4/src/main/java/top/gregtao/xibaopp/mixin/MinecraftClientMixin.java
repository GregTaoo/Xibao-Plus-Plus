package top.gregtao.xibaopp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.sound.MusicSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.gregtao.xibaopp.XibaoPlusPlusConfig;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "getMusicType", at = @At("TAIL"), cancellable = true)
    public void getMusicTypeInject(CallbackInfoReturnable<MusicSound> cir) {
        if (XibaoPlusPlusConfig.playMusic && XibaoPlusPlusConfig.shouldPlayMusic) {
            if (MinecraftClient.getInstance().currentScreen instanceof DisconnectedScreen) {
                cir.setReturnValue(XibaoPlusPlusConfig.type.music);
            }
        }
    }

}
