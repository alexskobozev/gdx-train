package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

import components.Position;
import components.Velocity;

public class MovementSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Velocity> vm;

    @SuppressWarnings("unchecked")
    public MovementSystem() {
        super(Aspect.getAspectForAll(Position.class, Velocity.class));
    }


    @Override
    protected void process(Entity entity) {
        Position position = pm.get(entity);
        Velocity velocity = vm.get(entity);

        position.x += velocity.vx * world.delta;
        position.y += velocity.vy * world.delta;

    }
}
