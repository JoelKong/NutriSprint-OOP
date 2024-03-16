package com.mygdx.game.GameLayer.Collisions;
import com.mygdx.game.GameLayer.Entity.GameEntity;
import com.mygdx.game.GameLayer.Entity.Player;
import com.mygdx.game.GameLayer.Sound.SoundManager;
import java.util.List;
import java.util.Map;

// Collision Manager Class
public class CollisionManager {
    private Collision collision;

    public CollisionManager() {
        this.collision = new Collision();
    }

    // Initialize all forms of collisions
    public void initializeCollisions(Map<String, List<GameEntity>> entityMap, SoundManager soundManager) {
        List<GameEntity> playerEntityList = entityMap.get("player");
        List<GameEntity> aiEntityList = entityMap.get("ai");

        for (GameEntity player: playerEntityList) {
            collision.gameWindowCollision(player);
            collision.handleAICollision(aiEntityList, (Player) player, soundManager);
            collision.handlePropCollisions(entityMap, soundManager);
        }
    }
}
