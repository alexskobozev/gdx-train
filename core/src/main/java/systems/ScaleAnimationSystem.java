package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

import components.ScaleAnimation;
import components.Sprite;

public class ScaleAnimationSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<ScaleAnimation> sa;
    @Mapper
    ComponentMapper<Sprite> sm;


    @SuppressWarnings("unchecked")
    public ScaleAnimationSystem() {
        super(Aspect.getAspectForAll(ScaleAnimation.class));
    }

    @Override
    protected void process(Entity entity) {
        ScaleAnimation scaleAnimation = sa.get(entity);
        if (scaleAnimation.active) {
            Sprite sprite = sm.get(entity);
            sprite.scaleX += scaleAnimation.speed * world.delta;

            if (sprite.scaleX > scaleAnimation.max) {
                sprite.scaleX = scaleAnimation.max;
                scaleAnimation.active = false;

            }else if (sprite.scaleX < scaleAnimation.min) {

                sprite.scaleX = scaleAnimation.min;
                scaleAnimation.active = false;

            }
            sprite.scaleY = sprite.scaleX;

        }

    }
}
