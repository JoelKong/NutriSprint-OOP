package com.mygdx.game.EngineLayer.Collisions;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;

import java.util.List;

// Collision Manager Class
public class CollisionManager {
    // Declare Attributes
    private Collision collision;

    // Default Constructor
    public CollisionManager() {
        this.collision = new Collision();
    }

    // Initialize all forms of collisions
    public void initializeCollisions(List<GameEntity> aiEntityList, List<GameEntity> playerEntityList) {
        // Prevents players from leaving game window.
        for (GameEntity player: playerEntityList) {
            collision.gameWindowCollision(player);
            collision.handleAICollision(aiEntityList, (Player) player);
        }
    }
}
