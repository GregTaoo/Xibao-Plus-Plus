package top.gregtao.xibaopp.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;

public class RenderHelper {
    public static void renderStretchTexture(int width, int height, int light, Identifier source) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderTexture(0, source);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        bufferBuilder.vertex(0.0F, (float) height, -1.0F).texture(0, 1).color(light, light, light, 255);
        bufferBuilder.vertex(width, height, -1.0F).texture(1, 1).color(light, light, light, 255);
        bufferBuilder.vertex(width, 0.0F, -1.0F).texture(1, 0).color(light, light, light, 255);
        bufferBuilder.vertex(0.0F, 0.0F, -1.0F).texture(0, 0).color(light, light, light, 255);
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
    }

    public static void renderParticle(int x, int y, int width, int height, Identifier source) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);
        RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX);
        RenderSystem.setShaderTexture(0, source);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        bufferBuilder.vertex(0.0F + x, height + y, 0.0F).texture(0, 1).color(255, 255, 255, 255);
        bufferBuilder.vertex(width + x, height + y, 0.0F).texture(1, 1).color(255, 255, 255, 255);
        bufferBuilder.vertex(width + x, 0.0F + y, 0.0F).texture(1, 0).color(255, 255, 255, 255);
        bufferBuilder.vertex(0.0F + x, 0.0F + y, 0.0F).texture(0, 0).color(255, 255, 255, 255);
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
    }
}
