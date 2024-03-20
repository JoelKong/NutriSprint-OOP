package com.mygdx.game.GameLayer.Collisions;
import com.mygdx.game.GameLayer.Entity.EntityManager;
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
    public void initializeCollisions(EntityManager entityManager, SoundManager soundManager) {
        collision.gameWindowCollision(entityManager.getPlayerEntityList());
        collision.handleAICollision(entityManager, soundManager);
        collision.handlePlayerCollisions(entityManager, soundManager);

    }
}
