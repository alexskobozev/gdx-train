package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import components.Sprite;
import components.SpriteAnimation;

public class SpriteAnimationSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<Sprite> sm;
    @Mapper
    ComponentMapper<SpriteAnimation> sam;

    @SuppressWarnings("unchecked")
    public SpriteAnimationSystem() {
        super(Aspect.getAspectForAll(Sprite.class, SpriteAnimation.class));
    }


    @Override
    protected void process(Entity entity) {
        Sprite sprite = sm.get(entity);
        SpriteAnimation animation = sam.get(entity);

        animation.stateTime += world.getDelta();

        TextureRegion region = animation.getFrame();
        sprite.x = region.getRegionX();
        sprite.y = region.getRegionY();
        sprite.height = region.getRegionHeight();
        sprite.width = region.getRegionWidth();

    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
