package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gradletest.EntityFactory;

import components.Player;
import components.Position;
import components.Velocity;

public class PlayerInputSystem extends EntityProcessingSystem implements InputProcessor {

    @Mapper
    ComponentMapper<Velocity> vm;
    @Mapper
    ComponentMapper<Position> pm;

    private OrthographicCamera camera;
    private Vector3 mouseVector;

    private int ax, ay;
    private int thruster = 400;
    private float drag = 0.4f;
    private boolean shoot;

    @SuppressWarnings("unchecked")
    public PlayerInputSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Velocity.class, Player.class, Position.class));
        this.camera = camera;
    }

    @Override
    protected void process(Entity entity) {

        mouseVector = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseVector);

        Velocity vel = vm.get(entity);
        Position pos = pm.get(entity);

        vel.vx += (ax - drag * vel.vx) * world.getDelta();
        vel.vy += (ay - drag * vel.vy) * world.getDelta();

        if (shoot) {
            EntityFactory.createBullet(world, pos.x + 7, pos.y + 40).addToWorld();
            EntityFactory.createBullet(world, pos.x + 60, pos.y + 40).addToWorld();

        }

    }

    @Override
    protected void initialize() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) ay = thruster;
        if (keycode == Input.Keys.DOWN) ay = -thruster;
        if (keycode == Input.Keys.RIGHT) ax = thruster;
        if (keycode == Input.Keys.LEFT) ax = -thruster;
        if (keycode == Input.Keys.SPACE) shoot = true;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) ay = 0;
        if (keycode == Input.Keys.DOWN) ay = 0;
        if (keycode == Input.Keys.RIGHT) ax = 0;
        if (keycode == Input.Keys.LEFT) ax = 0;
        if (keycode == Input.Keys.SPACE) shoot = false;
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
