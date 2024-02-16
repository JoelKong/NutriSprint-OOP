package com.mygdx.game.Collisions;
import com.mygdx.game.Entity.GameEntity;
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

    // Initialize all types of collision checks
    public void initializeCollisions(Map<String, List<GameEntity>> entityMap) {
        collision.collidePlayerAI(entityMap);
        collision.collidePlayerGameWindow(entityMap);
    }
}
