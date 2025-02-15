package top.gregtao.xibaopp.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Inject(method = "renderBackground(Lnet/minecraft/client/gui/DrawContext;IIF)V", at = @At("HEAD"), cancellable = true)
    public void renderBackgroundInject(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (screen instanceof DisconnectedScreen) {
            ci.cancel();
        }
    }
}
