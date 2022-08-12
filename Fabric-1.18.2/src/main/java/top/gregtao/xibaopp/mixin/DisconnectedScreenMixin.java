package top.gregtao.xibaopp.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.gregtao.xibaopp.XibaoPlusPlusConfig;
import top.gregtao.xibaopp.util.RenderHelper;

@Environment(EnvType.CLIENT)
@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin extends Screen {

    protected DisconnectedScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render",
            at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;"))
    private void renderInject(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Text reason = ((DisconnectedScreenAccessor) this).getReason();
        if (reason instanceof TranslatableText text) {
            if (text.getKey().startsWith("multiplayer.disconnect.banned")) { //人性化设计
                XibaoPlusPlusConfig.shouldPlayMusic = false;
                return;
            }
        }
        XibaoPlusPlusConfig.shouldPlayMusic = true;
        RenderHelper.renderStretchTexture(this.width, this.height, 180,
                new Identifier("xibaopp", "textures/xibao.png"));
    }
}
