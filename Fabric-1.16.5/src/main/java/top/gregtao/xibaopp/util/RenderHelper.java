package top.gregtao.xibaopp.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;

public class RenderHelper {
    public static void renderStretchTexture(MinecraftClient client, int width, int height, int light, Identifier source) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        client.getTextureManager().bindTexture(source);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(0.0D, height, 0.0D).texture(0, 1).color(light, light, light, 255).next();
        bufferBuilder.vertex(width, height, 0.0D).texture(1, 1).color(light, light, light, 255).next();
        bufferBuilder.vertex(width, 0.0D, 0.0D).texture(1, 0).color(light, light, light, 255).next();
        bufferBuilder.vertex(0.0D, 0.0D, 0.0D).texture(0, 0).color(light, light, light, 255).next();
        tessellator.draw();
    }

    public static void renderParticle(MinecraftClient client, int x, int y, int width, int height, Identifier source) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        client.getTextureManager().bindTexture(source);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableAlphaTest();
        bufferBuilder.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        bufferBuilder.vertex(0.0D + x, height + y, 0.0D).texture(0, 1).color(255, 255, 255, 255).next();
        bufferBuilder.vertex(width + x, height + y, 0.0D).texture(1, 1).color(255, 255, 255, 255).next();
        bufferBuilder.vertex(width + x, 0.0D + y, 0.0D).texture(1, 0).color(255, 255, 255, 255).next();
        bufferBuilder.vertex(0.0D + x, 0.0D + y, 0.0D).texture(0, 0).color(255, 255, 255, 255).next();
        tessellator.draw();
    }
}
