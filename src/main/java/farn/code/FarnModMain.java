package farn.code;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.Minecraft;
import java.io.*;

public class FarnModMain implements ModInitializer {
    public static final String MOD_ID = "CoordinateMod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static File configFile = new File((Minecraft.getMinecraftDir()) + "/config/CoordinatesMod.cfg");
    public static int LabelX = getFromConfig("LabelX");
    public static int LabelY = getFromConfig("LabelY");
    public static int ToggleKey = getFromConfig("ToggleKey");

    @Override
    public void onInitialize() {
        LOGGER.info("Farn Mod initialized.");
    }

    public static void writeConfig() {
        try {
            BufferedWriter configWriter = new BufferedWriter(new FileWriter(configFile));
            configWriter.write("// Farn's Coordinates Mod. Configure options here.");
            configWriter.write(System.getProperty("line.separator") + "LabelX=2");
            configWriter.write(System.getProperty("line.separator") + "LabelY=128");
            configWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static int getFromConfig(String s2) {
        if(!configFile.exists()) {
            writeConfig();
        }
        try {
            BufferedReader configReader = new BufferedReader(new FileReader(configFile));
            String s;
            while ((s = configReader.readLine()) != null) {
                if (s.charAt(0) == '/' && s.charAt(1) == '/') {
                    continue;
                }
                else if (s.contains("=")) {
                    String as[] = s.split("=");
                    String name = as[0];
                    int id = Integer.parseInt(as[1]);
                    if (name.equals(s2)){
                        return id;
                    } else {
                        continue;
                    }
                }
            }

            configReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
        return 0;
    }
}