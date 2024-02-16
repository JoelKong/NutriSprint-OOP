package com.mygdx.game.Collisions;

import com.mygdx.game.Entity.GameEntity;

import java.util.List;
import java.util.Map;

public class CollisionManager {
    private Collision collision;

    public CollisionManager() {
        this.collision = new Collision();
    }

    public void initializeCollisions(Map<String, List<GameEntity>> entityMap) {
        collision.collidePlayerAI(entityMap);
        collision.collidePlayerGameWindow(entityMap);
    }
}
