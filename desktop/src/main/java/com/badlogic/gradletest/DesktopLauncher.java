
package com.badlogic.gradletest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import utils.ImagePacker;

public class DesktopLauncher {

    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 900;


    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = FRAME_WIDTH;
        config.height = FRAME_HEIGHT;
        config.useGL20 = true;
        config.title = "GameXYZ";
        config.vSyncEnabled = false;
        config.resizable=false;

        ImagePacker.run();
        new LwjglApplication(new GameXYZ(FRAME_WIDTH,FRAME_HEIGHT), config);
    }

}
