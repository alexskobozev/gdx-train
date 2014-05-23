package com.badlogic.gradletest;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import systems.ColorAnimationSystem;
import systems.ExpiringSystem;
import systems.ScaleAnimationSystem;
import systems.SpriteAnimationSystem;

public class GameXYZ extends Game {

    public int windowWidth;
    public int windowHeight;

    public World world;
    public SpriteBatch spriteBatch;

    public GameXYZ(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    public void create() {

        world = new World();
        spriteBatch = new SpriteBatch();

        world.setSystem(new SpriteAnimationSystem());
        world.setSystem(new ScaleAnimationSystem());
        world.setSystem(new ExpiringSystem());
        world.setSystem(new ColorAnimationSystem());
        world.initialize();

        setScreen(new OverworldScreen);
    }


}
