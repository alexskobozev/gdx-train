package com.badlogic.gradletest;

import com.artemis.Entity;
import com.artemis.World;

import components.Expires;
import components.Player;
import components.Position;
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

        return e;
    }

    public static Entity createEnemyShip(World world, String name, Sprite.Layer layer,
                                         float x, float y, float vx, float vy) {


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

        return entity;
    }

}
