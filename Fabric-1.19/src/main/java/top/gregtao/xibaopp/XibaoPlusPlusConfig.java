package top.gregtao.xibaopp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class XibaoPlusPlusConfig {
    private static final File file = new File("xibao_plus_plus.properties");

    public static boolean shouldPlayMusic = false;
    public static boolean tempSnow = false;
    public static Random random = new Random();

    public static boolean playMusic = true;
    public static boolean showPicture = true;
    public static boolean displaySnow = false;

    public static XibaoType type = XibaoType.XIBAO;

    public static void loadConfig() throws Exception {
        if (!file.exists()) {
            storeConfig();
            return;
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        playMusic = properties.getOrDefault("playMusic", "true").equals("true");
        showPicture = properties.getOrDefault("showPicture", "true").equals("true");
        displaySnow = properties.getOrDefault("displaySnow", "false").equals("true");
        type = XibaoType.getByString(properties.getProperty("type"));
    }

    public static void storeConfig() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("playMusic", String.valueOf(playMusic));
        properties.setProperty("showPicture", String.valueOf(showPicture));
        properties.setProperty("displaySnow", String.valueOf(displaySnow));
        properties.setProperty("type", type.type);
        properties.store(new FileWriter(file), "Xibao Plus Plus");
    }

    public static void switchAlbum() {
        XibaoType[] types = XibaoType.values();
        type = types[(type.ordinal() + 1) % types.length];
    }
}
