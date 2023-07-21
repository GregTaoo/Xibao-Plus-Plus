package top.gregtao.xibaopp;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class XibaoPlusPlusMusic {
    public static RegistryEntry.Reference<SoundEvent> XIBAO_SOUND_EVENT = registerReference(new Identifier("xibaopp", "xibao"));
    public static MusicSound XIBAO_MUSIC = new MusicSound(XIBAO_SOUND_EVENT, 0, 0, true);
    public static RegistryEntry.Reference<SoundEvent> BEIBAO_SOUND_EVENT = registerReference(new Identifier("xibaopp", "beibao"));
    public static MusicSound BEIBAO_MUSIC = new MusicSound(BEIBAO_SOUND_EVENT, 0, 0, true);

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }
    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }
}
