package com.mygdx.game.EngineLayer.Collisions;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;

import java.util.List;
import java.util.Map;

// Collision Manager Class
public class CollisionManager {
    // Declare Attributes
    private Collision collision;

    // Default Constructor
    public CollisionManager() {
        this.collision = new Collision();
    }

    // Initialize all forms of collisions
    public void initializeCollisions(Map<String, List<GameEntity>> entityMap) {
        List<GameEntity> playerEntityList = entityMap.get("player");
        List<GameEntity> aiEntityList = entityMap.get("ai");
        List<GameEntity> propEntityList = entityMap.get("props");

        for (GameEntity player: playerEntityList) {
            collision.gameWindowCollision(player);
            collision.handleAICollision(aiEntityList, (Player) player);
            collision.handlePropCollisions(entityMap);
        }
    }
}
