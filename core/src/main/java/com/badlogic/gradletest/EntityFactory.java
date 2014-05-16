package com.badlogic.gradletest;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.math.MathUtils;

import components.Bounds;
import components.ColorAnimation;
import components.Expires;
import components.Health;
import components.Player;
import components.Position;
import components.ScaleAnimation;
import components.Sprite;
import components.Velocity;

public class EntityFactory {

    public static Entity createPlayer(World world, float x, float y) {
        Entity e = world.createEntity();

        Position position = new Position();
        position.x = x;
        position.y = y;
        e.addComponent(position);

        Sprite sprite = new Sprite("fighter", Sprite.Layer.ACTORS_3);
        sprite.r = 93 / 255f;
        sprite.g = 255 / 255f;
        sprite.b = 129 / 255f;
        e.addComponent(sprite);

        Velocity velocity = new Velocity(0, 0);
        e.addComponent(velocity);
        e.addComponent(new Player());

        Bounds bounds = new Bounds();
        bounds.radius = 43;
        e.addComponent(bounds);

        e.addComponent(new Player());

        world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_SHIP);

        return e;
    }

    public static Entity createBullet(World world, float x, float y) {
        Entity e = world.createEntity();

        Position position = new Position();
        position.x = x;
        position.y = y;
        e.addComponent(position);

        Sprite sprite = new Sprite();
        sprite.name = "bullet";
        sprite.layer = Sprite.Layer.PARTICLES;
        e.addComponent(sprite);

        Velocity velocity = new Velocity(0, 800);
        e.addComponent(velocity);

        Expires expires = new Expires(2f);
        e.addComponent(expires);


        Bounds bounds = new Bounds();
        bounds.radius = 5;
        e.addComponent(bounds);

        world.getManager(GroupManager.class).add(e, Constants.Groups.PLAYER_BULLETS);


        return e;
    }

    public static Entity createEnemyShip(World world, String name, Sprite.Layer layer, float health,
                                         float x, float y, float vx, float vy, float boundsRadius) {


        Entity entity = world.createEntity();

        Position position = new Position();
        position.x = x;
        position.y = y;
        entity.addComponent(position);

        Sprite sprite = new Sprite();
        sprite.name = name;
        sprite.r = 255 / 255f;
        sprite.g = 0 / 255f;
        sprite.b = 142 / 255f;
        sprite.layer = layer;
        entity.addComponent(sprite);

        Velocity velocity = new Velocity(vx, vy);
        entity.addComponent(velocity);

        Bounds bounds = new Bounds();
        bounds.radius = boundsRadius;
        entity.addComponent(bounds);

        Health h = new Health();
        h.health = h.maxHealth = health;
        entity.addComponent(h);

        world.getManager(GroupManager.class).add(entity, Constants.Groups.ENEMY_SHIPS);


        return entity;
    }

    public static Entity createParticle(World world, float x, float y) {
        Entity e = world.createEntity();

        Position position = new Position();
        position.x = x;
        position.y = y;
        e.addComponent(position);

        Sprite sprite = new Sprite();

        sprite.name = "particle";
        sprite.scaleX = sprite.scaleY = MathUtils.random(0.3f, 0.6f);
        sprite.r = 1;
        sprite.g = 216 / 255f;
        sprite.b = 0;
        sprite.a = 0.5f;
        sprite.layer = Sprite.Layer.PARTICLES;
        e.addComponent(sprite);

        float radiance = MathUtils.random(MathUtils.PI2);
        float magnitude = MathUtils.random(400f);

        Velocity velocity = new Velocity(magnitude * MathUtils.cos(radiance),
                magnitude * MathUtils.sin(radiance));

        e.addComponent(velocity);

        Expires expires = new Expires();
        expires.delay = 1;
        e.addComponent(expires);

        ColorAnimation colorAnimation = new ColorAnimation();
        colorAnimation.alphaAnimate = true;
        colorAnimation.alphaSpeed = -1f;
        colorAnimation.alphaMin = 0f;
        colorAnimation.alphaMax = 1f;
        colorAnimation.repeat = false;
        e.addComponent(colorAnimation);

        return e;
    }

    public static Entity createExplosion(World world, float x, float y, float scale) {
        Entity e = world.createEntity();

        Position position = new Position();
        position.x = x;
        position.y = y;
        e.addComponent(position);

        Sprite sprite = new Sprite();
        sprite.name = "explosion";
        sprite.scaleX = sprite.scaleY = scale;
        sprite.r = 1;
        sprite.g = 216/255f;
        sprite.b = 0;
        sprite.a = 0.5f;
        sprite.layer = Sprite.Layer.PARTICLES;
        e.addComponent(sprite);

        Expires expires = new Expires();
        expires.delay = 0.5f;
        e.addComponent(expires);


        ScaleAnimation scaleAnimation = new ScaleAnimation();
        scaleAnimation.active = true;
        scaleAnimation.max = scale;
        scaleAnimation.min = scale/100f;
        scaleAnimation.speed = -3.0f;
        scaleAnimation.repeat = false;
        e.addComponent(scaleAnimation);

        return e;
    }
}
