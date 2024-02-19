package com.mygdx.game.Collisions;
import com.mygdx.game.Entity.GameEntity;
import com.mygdx.game.Entity.Player;

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
