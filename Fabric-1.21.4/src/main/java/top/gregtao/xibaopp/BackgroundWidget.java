package top.gregtao.xibaopp;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.util.Window;
import top.gregtao.xibaopp.util.RenderHelper;

public class BackgroundWidget implements Drawable, Element, Selectable {

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        Window window = client.getWindow();
        int width = window.getScaledWidth(), height = window.getScaledHeight();
        if (XibaoPlusPlusConfig.showPicture) {
            RenderHelper.renderStretchTexture(width, height, 225,
                    XibaoPlusPlusConfig.type.background);
        }
        if (XibaoPlusPlusConfig.displaySnow || XibaoPlusPlusConfig.tempSnow) {
            SnowAnimation.INSTANCE.tick(width, height, XibaoPlusPlusConfig.type.snows);
        }
    }

    @Override
    public void setFocused(boolean focused) {
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    @Override
    public SelectionType getType() {
        return Selectable.SelectionType.NONE;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {
    }
}
