package top.gregtao.xibaopp;

import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class XibaoPlusPlusMusic {
    public static SoundEvent XIBAO_SOUND_EVENT = new SoundEvent(new Identifier("xibaopp", "xibao"));
    public static MusicSound XIBAO_MUSIC = new MusicSound(XIBAO_SOUND_EVENT, 0, 0, true);
    public static SoundEvent BEIBAO_SOUND_EVENT = new SoundEvent(new Identifier("xibaopp", "beibao"));
    public static MusicSound BEIBAO_MUSIC = new MusicSound(BEIBAO_SOUND_EVENT, 0, 0, true);
}
