package com.mygdx.game.GameLayer.Collisions;
import com.mygdx.game.EngineLayer.EngineCollisionManager;
import com.mygdx.game.GameLayer.Entity.GameEntity;
import java.util.List;

// Collision Manager Class
public class CollisionManager extends EngineCollisionManager {
    // Declare Attributes
    private Collision collision;

    // Default Constructor
    public CollisionManager() {
        this.collision = new Collision();
    }

    // Initialize all forms of collisions
    public void initializeCollisions(List<GameEntity> aiEntityList, List<GameEntity> playerEntityList) {
        // Prevents players from leaving game window.
        for (GameEntity player: playerEntityList) { collision.gameWindowCollision(player); }

        // Loops through both lists and checks for collisions between the player and other entities.
        for (GameEntity player : playerEntityList) {
            for (GameEntity ai : aiEntityList) {
                if (collision.collisionDetected(player, ai)) {
                    ai.setPopFromScreen(true);
                }
            }
        }
    }
}
