package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import components.Position;
import components.Sprite;

public class SpriteRenderSystem extends EntitySystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Sprite> sm;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    @SuppressWarnings("unchecked")
    public SpriteRenderSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Position.class, Sprite.class));
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
    }

    public SpriteRenderSystem(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        for (int i = 0; i < entityImmutableBag.size(); i++) {
            process(entityImmutableBag.get(i));
        }
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    private void process(Entity entity) {
        if (pm.has(entity)) {
            Position position = pm.getSafe(entity);
            Sprite sprite = sm.getSafe(entity);
            batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
            float posX = position.x;
            float posY = position.y;

            batch.draw(sprite.sprite, posX, posY);
        }
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }
}
