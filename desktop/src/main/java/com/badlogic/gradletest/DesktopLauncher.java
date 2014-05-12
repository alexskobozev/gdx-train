
package com.badlogic.gradletest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher extends Game {

    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 900;


    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = FRAME_WIDTH;
        config.height = FRAME_HEIGHT;
        config.useGL20 = true;
        config.title = "GameXYZ";
        new LwjglApplication(new Launcher(), config);
    }

    @Override
    public void create() {
        setScreen(new GameXYZ(this));
    }
}
