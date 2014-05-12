package systems;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gradletest.EntityFactory;

import components.Sprite;


public class EntitySpawningTimerSystem extends VoidEntitySystem {

    private Timer timer1;
    private Timer timer2;
    private Timer timer3;

    public EntitySpawningTimerSystem() {

        timer1 = new Timer(2, true) {

            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy1", Sprite.Layer.ACTORS_3,
                        MathUtils.random(0, 1280), 900 + 100, 0, -30).addToWorld();
            }
        };

        timer2 = new Timer(6, true) {
            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy2", Sprite.Layer.ACTORS_2,
                        MathUtils.random(0, 1280), 900 + 50, 0, -40).addToWorld();
            }
        };

        timer3 = new Timer(12, true) {
            @Override
            public void execute() {
                EntityFactory.createEnemyShip(world, "enemy3", Sprite.Layer.ACTORS_1,
                        MathUtils.random(0, 1280), 900 + 200, 0, -20).addToWorld();
            }
        };
    }

    @Override
    protected void processSystem() {
        timer1.update(world.delta);
        timer2.update(world.delta);
        timer3.update(world.delta);
    }
}
