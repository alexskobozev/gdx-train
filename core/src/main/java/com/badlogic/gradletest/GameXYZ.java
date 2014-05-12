package com.badlogic.gradletest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameXYZ implements Screen {

    OrthographicCamera camera;


    public GameXYZ(Game game) {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 900);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();

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
