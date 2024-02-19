package com.mygdx.game.Collisions;
import com.badlogic.gdx.Game;
import com.mygdx.game.Entity.AI;
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

    // Initialize all types of collision checks
    public void initializeCollisions(Map<String, List<GameEntity>> entityMap) {
        List<GameEntity> PlayerList = entityMap.get("player");  // List of player entities
        List<GameEntity> AIList = entityMap.get("spawnables");
        GameEntity player = PlayerList.get(0);

        for (GameEntity aiEntity : AIList) {
            if (collision.CollisionDetected(player, aiEntity))
            {
                aiEntity.setPopFromScreen(true);
            }
        }

        //collision.collidePlayerAI(entityMap);
        collision.collidePlayerGameWindow(entityMap);
    }
}
