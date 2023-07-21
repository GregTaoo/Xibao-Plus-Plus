package top.gregtao.xibaopp;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class XibaoPlusPlusMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        try {
            XibaoPlusPlusConfig.loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}