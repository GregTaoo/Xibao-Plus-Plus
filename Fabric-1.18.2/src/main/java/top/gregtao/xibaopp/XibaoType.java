package top.gregtao.xibaopp;

import net.minecraft.sound.MusicSound;
import net.minecraft.util.Identifier;

public enum XibaoType {
    XIBAO(
            XibaoPlusPlusMusic.XIBAO_MUSIC,
            new Identifier("xibaopp", "textures/xibao.png"),
            new Identifier[] {
                    new Identifier("xibaopp", "textures/yellow_snow.png"),
                    new Identifier("xibaopp", "textures/red_snow.png")
            },
            "xibao"
    ),
    BEIBAO(
            XibaoPlusPlusMusic.BEIBAO_MUSIC,
            new Identifier("xibaopp", "textures/beibao.png"),
            new Identifier[] {
                    new Identifier("xibaopp", "textures/white_snow.png")
            },
            "beibao"
    )
    ;
    public MusicSound music;
    public Identifier background;
    public Identifier[] snows;
    public String type;

    XibaoType(MusicSound music, Identifier background, Identifier[] snows, String type) {
        this.music = music;
        this.background = background;
        this.snows = snows;
        this.type = type;
    }

    public static XibaoType getByString(String type) {
        for (XibaoType xibaoType : values()) {
            if (type.equals(xibaoType.type)) return xibaoType;
        }
        return XIBAO;
    }
}
