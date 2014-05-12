package com.badlogic.gradletest;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

import systems.CollisionSystem;
import systems.EntitySpawningTimerSystem;
import systems.ExpiringSystem;
import systems.MovementSystem;
import systems.PlayerInputSystem;
import systems.SpriteRenderSystem;

public class GameXYZ implements Screen {

    private final Game game;
    private final World world;
    private final SpriteRenderSystem spriteRenderSystem;
    OrthographicCamera camera;


    public GameXYZ(Game game) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 900);

        this.game = game;

        world = new World();
        spriteRenderSystem = world.setSystem(new SpriteRenderSystem(camera), true);

        world.setSystem(new PlayerInputSystem(camera));
        world.setSystem(new MovementSystem());
        world.setSystem(new CollisionSystem());
        world.setSystem(new ExpiringSystem());
        world.setSystem(new EntitySpawningTimerSystem());

        world.setManager(new GroupManager());

        world.initialize();

        EntityFactory.createPlayer(world, 150, 150).addToWorld();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();

        world.setDelta(delta);
        world.process();
        spriteRenderSystem.process();

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resize");
    }

    @Override
    public void show() {
        System.out.println("Show");
    }

    @Override
    public void hide() {
        System.out.println("Hide");
    }

    @Override
    public void pause() {
        System.out.println("Pause");
    }

    @Override
    public void resume() {
        System.out.println("Resume");
    }

    @Override
    public void dispose() {
        System.out.println("Dispose");
    }
}
