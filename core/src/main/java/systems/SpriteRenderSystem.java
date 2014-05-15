package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import components.Position;
import components.Sprite;

public class SpriteRenderSystem extends EntitySystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Sprite> sm;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private TextureAtlas textureAtlas;
    private HashMap<String, TextureAtlas.AtlasRegion> regions;
    private Bag<TextureAtlas.AtlasRegion> regionsByEntity;

    private List<Entity> sortedEntities;


    @SuppressWarnings("unchecked")
    public SpriteRenderSystem(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Position.class, Sprite.class));
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        regions = new HashMap<String, TextureAtlas.AtlasRegion>();
        textureAtlas = new TextureAtlas(Gdx.files.internal("resources/textures/pack.atlas"), Gdx.files.internal("resources/textures"));
        for (TextureAtlas.AtlasRegion r : textureAtlas.getRegions()) {
            regions.put(r.name, r);
        }
        regionsByEntity = new Bag<TextureAtlas.AtlasRegion>();
        batch = new SpriteBatch();
        sortedEntities = new ArrayList<Entity>();
    }


    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        for (Entity e : sortedEntities) {
            process(e);
        }
    }

    @Override
    protected void begin() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    protected void inserted(Entity e) {
        Sprite sprite = sm.get(e);
        regionsByEntity.set(e.getId(), regions.get(sprite.name));

        sortedEntities.add(e);

        Collections.sort(sortedEntities, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                Sprite s1 = sm.get(o1);
                Sprite s2 = sm.get(o2);
                return s1.layer.compareTo(s2.layer);
            }
        });
    }

    @Override
    protected void removed(Entity e) {
        regionsByEntity.set(e.getId(), null);
        sortedEntities.remove(e);
    }

    private void process(Entity entity) {
        if (pm.has(entity)) {
            Position position = pm.getSafe(entity);
            Sprite sprite = sm.get(entity);

            TextureAtlas.AtlasRegion spriteRegion = regionsByEntity.get(entity.getId());
            batch.setColor(sprite.r, sprite.g, sprite.b, sprite.a);
            float posX = position.x -
                    (spriteRegion.getRegionWidth() / 2 *
                            sprite.scaleX);
            float posY = position.y - (spriteRegion.getRegionHeight() / 2 * sprite.scaleX);

            batch.draw(spriteRegion, posX, posY, 0, 0,
                    spriteRegion.getRegionWidth(), spriteRegion.getRegionHeight(),
                    sprite.scaleX, sprite.scaleY, sprite.rotation
            );
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
