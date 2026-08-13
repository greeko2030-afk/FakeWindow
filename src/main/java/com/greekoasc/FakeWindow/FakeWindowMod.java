package com.greekoasc.fakewindow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class FakeWindowMod implements ClientModInitializer {
    
    public static final String MOD_ID = "fakewindow";
    public static final Logger LOGGER = LoggerFactory.getLogger("GreekoASC-" + MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing FakeWindow by GreekoASC...");
        LOGGER.info("Borderless Window and Fake Resolution Scaling Ready!");
        
        // You can register keybindings here later (e.g., F11 toggle)
    }
}

