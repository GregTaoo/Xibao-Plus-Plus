package top.gregtao.xibaopp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class XibaoPlusPlusMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Registry.register(Registry.SOUND_EVENT, XibaoPlusPlusMusic.XIBAO_SOUND_EVENT.getId(), XibaoPlusPlusMusic.XIBAO_SOUND_EVENT);
    }
}