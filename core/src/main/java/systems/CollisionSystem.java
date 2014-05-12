package systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.Utils;
import com.badlogic.gradletest.Constants;

import components.Bounds;
import components.Health;
import components.Position;

public class CollisionSystem extends EntitySystem {

    @Mapper
    ComponentMapper<Position> pm;
    @Mapper
    ComponentMapper<Bounds> bm;
    @Mapper
    ComponentMapper<Health> hm;

    private Bag<CollisionPair> collisionPairs;

    @SuppressWarnings("unchecked")
    public CollisionSystem() {
        super(Aspect.getAspectForAll(Position.class, Bounds.class));
    }

    @Override
    protected void initialize() {

        collisionPairs = new Bag<CollisionPair>();

        collisionPairs.add(new CollisionPair(Constants.Groups.PLAYER_BULLETS,
                Constants.Groups.ENEMY_SHIPS, new CollisionHandler() {

            @Override
            public void handleCollision(Entity bullet, Entity ship) {


                Health health = hm.get(ship);
                health.health -= 10;
                bullet.deleteFromWorld();

                if (health.health <= 0) {
                    ship.deleteFromWorld();
                }

            }
        }
        ));
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entityImmutableBag) {
        for (int i = 0; i < collisionPairs.size(); i++) {
            collisionPairs.get(i).checkForCollisions();
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    private class CollisionPair {

        private ImmutableBag<Entity> groupEntitiesA;
        private ImmutableBag<Entity> groupEntitiesB;
        private CollisionHandler handler;


        public CollisionPair(String group1, String group2, CollisionHandler handler) {

            groupEntitiesA = world.getManager(GroupManager.class).getEntities(group1);
            groupEntitiesB = world.getManager(GroupManager.class).getEntities(group2);

            this.handler = handler;
        }

        public void checkForCollisions() {

            for (int a = 0; groupEntitiesA.size() > a; a++) {
                for (int b = 0; groupEntitiesB.size() > b; b++) {
                    Entity entityA = groupEntitiesA.get(a);
                    Entity entityB = groupEntitiesB.get(b);
                    if (collisionExists(entityA, entityB)) {
                        handler.handleCollision(entityA, entityB);
                    }
                }
            }

        }

        private boolean collisionExists(Entity entity1, Entity entity2) {
            Position p1 = pm.get(entity1);
            Position p2 = pm.get(entity2);

            Bounds b1 = bm.get(entity1);
            Bounds b2 = bm.get(entity2);
           // return Utils.distance(p1.x, p1.y, p2.x, p2.y) - b1.radius < b2.radius;
            return Utils.doCirclesCollide(p1.x, p1.y, b1.radius, p2.x, p2.y, b2.radius);
        }
    }

    private interface CollisionHandler {
        void handleCollision(Entity a, Entity b);
    }
}
