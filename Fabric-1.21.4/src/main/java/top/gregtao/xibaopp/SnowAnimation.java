package top.gregtao.xibaopp;

import net.minecraft.util.Identifier;
import top.gregtao.xibaopp.util.RenderHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowAnimation {
    public static SnowAnimation INSTANCE;
    private Identifier[] source;
    private int lastWidth, lastHeight;
    private float amount = 0.1f;
    private final Random random;
    private final List<Snow> snows;

    public SnowAnimation(Random random) {
        this.random = random;
        this.snows = new ArrayList<>();
    }

    public void tick(int width, int height, Identifier[] source) {
        this.source = source;
        if (width != this.lastWidth || height != this.lastHeight) {
            this.snows.clear();
            this.lastWidth = width;
            this.lastHeight = height;
        }
        this.spawnGroup();
        for (Snow snow : this.snows) {
            RenderHelper.renderParticle(snow.x, height - snow.y, 4, 4, this.source[snow.source % this.source.length]);
            snow.move();
            if (snow.y > height) {
                snow.removed = true;
            }
        }
        if (this.snows.size() > 500) {
            int i = 0;
            while (i < this.snows.size() && this.snows.get(i).removed) this.snows.remove(i);
        }
    }

    public void spawnGroup() {
        this.amount += 0.001f;
        float realAmount = Math.min(0.4f, this.amount);
        if (realAmount < 1) {
            if (this.random.nextInt((int) (1 / realAmount)) == 0) this.spawn();
        } else {
            for (int i = 1; i <= realAmount; ++i) this.spawn();
        }
        if (this.amount >= 0.6f) this.amount = 0.1f;
    }

    public int getSourceIndex() {
        return this.random.nextInt(this.source.length);
    }

    public void spawn() {
        int x = random.nextInt(this.lastWidth);
        int k = (random.nextInt(4)) * 2 + 1;
        this.snows.add(new Snow(x, random.nextInt(2) == 0 ? -k : k, this.getSourceIndex()));
    }

}

class Snow {
    public int baseX, x, y, k, source;
    public boolean removed = false;
    public Snow(int x, int k, int source) {
        this.baseX = this.x = x;
        this.k = k;
        this.source = source;
        this.y = 0;
    }
    public void move() {
        this.y++;
        this.x = this.baseX + this.y / this.k;
    }
}