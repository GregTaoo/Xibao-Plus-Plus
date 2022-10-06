package top.gregtao.xibaopp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
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
        if (reason instanceof TranslatableText text) {
            if (text.getKey().startsWith("multiplayer.disconnect.banned")) { //人性化设计
                XibaoPlusPlusConfig.shouldPlayMusic = false;
            }
        }
        this.addDrawableChild(new ButtonWidget(
                this.width / 2 - 100, this.height - 22,
                 XibaoPlusPlusConfig.displaySnow ? 200 : 99, 20, new TranslatableText("gui.stopMusic"), button -> XibaoPlusPlusConfig.shouldPlayMusic = false));
        if (!XibaoPlusPlusConfig.displaySnow) {
            this.addDrawableChild(new ButtonWidget(
                    this.width / 2 + 2, this.height - 22,
                    99, 20, new TranslatableText("gui.dropSnow"),
                    button -> {
                        SnowAnimation.INSTANCE = new SnowAnimation(XibaoPlusPlusConfig.random);
                        XibaoPlusPlusConfig.tempSnow = true;
                    }));
        }
        if (XibaoPlusPlusConfig.displaySnow) SnowAnimation.INSTANCE = new SnowAnimation(XibaoPlusPlusConfig.random);
    }

    @Inject(method = "render",
            at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;"))
    private void renderInject(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (XibaoPlusPlusConfig.showPicture) RenderHelper.renderStretchTexture(this.width, this.height, 225,
                new Identifier("xibaopp", "textures/xibao.png"));
        if (XibaoPlusPlusConfig.displaySnow || XibaoPlusPlusConfig.tempSnow) SnowAnimation.INSTANCE.tick(this.width, this.height);
    }
}
