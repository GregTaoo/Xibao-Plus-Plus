package top.gregtao.xibaopp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.gregtao.xibaopp.SnowAnimation;
import top.gregtao.xibaopp.XibaoPlusPlusConfig;
import top.gregtao.xibaopp.util.RenderHelper;

@Environment(EnvType.CLIENT)
@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin extends Screen {

    protected DisconnectedScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void initInject(CallbackInfo ci) {
        XibaoPlusPlusConfig.shouldPlayMusic = true;
        XibaoPlusPlusConfig.tempSnow = false;
        Text reason = ((DisconnectedScreenAccessor) this).getReason();
        if (reason instanceof TranslatableText) {
            TranslatableText text = (TranslatableText) reason;
            if (text.getKey().startsWith("multiplayer.disconnect.banned")) { //人性化设计
                XibaoPlusPlusConfig.shouldPlayMusic = false;
            }
        }
        this.addButton(new ButtonWidget(
                this.width / 2 - 100, this.height - 22,
                66, 20, new TranslatableText("gui.stopMusic"),
                button -> XibaoPlusPlusConfig.shouldPlayMusic = !XibaoPlusPlusConfig.shouldPlayMusic));
        this.addButton(new ButtonWidget(
                this.width / 2 - 33, this.height - 22,
                66, 20, new TranslatableText("gui.dropSnow"),
                button -> {
                    SnowAnimation.INSTANCE = new SnowAnimation(XibaoPlusPlusConfig.random);
                    XibaoPlusPlusConfig.tempSnow = !XibaoPlusPlusConfig.tempSnow;
                }));
        this.addButton(new ButtonWidget(
                this.width / 2 + 34, this.height - 22,
                66, 20, new TranslatableText("gui.switchAlbum"),
                button -> XibaoPlusPlusConfig.switchAlbum()));
        if (XibaoPlusPlusConfig.displaySnow) SnowAnimation.INSTANCE = new SnowAnimation(XibaoPlusPlusConfig.random);
    }

    @Inject(method = "render", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/font/MultilineText;drawCenterWithShadow(Lnet/minecraft/client/util/math/MatrixStack;II)I"))
    private void renderInject(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.client == null) return;
        if (XibaoPlusPlusConfig.showPicture) {
            RenderHelper.renderStretchTexture(this.client, this.width, this.height, 225,
                    XibaoPlusPlusConfig.type.background);
        }
        if (XibaoPlusPlusConfig.displaySnow || XibaoPlusPlusConfig.tempSnow) {
            SnowAnimation.INSTANCE.tick(this.client, this.width, this.height, XibaoPlusPlusConfig.type.snows);
        }
    }
}
