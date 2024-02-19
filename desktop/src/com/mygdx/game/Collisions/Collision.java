package com.mygdx.game.Collisions;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entity.AI;
import com.mygdx.game.Entity.GameEntity;
import com.mygdx.game.Entity.Player;
import java.util.List;
import java.util.Map;

// Collision class for all kinds of collisions
public class Collision {
    // Default Constructor
    protected Collision() {}

    // Detects collisions between 2 GameEntities
    public boolean CollisionDetected(GameEntity A, GameEntity B) {
        if (A.getHitbox().overlaps(B.getHitbox())) {
            return true;
        } else return false;
    }

    // Collision of Player with the Game Window
    protected void collidePlayerGameWindow(Map<String,List<GameEntity>> entityMap) {
        for (GameEntity playerEntity: entityMap.get("player")) {
            // Left Boundary
            if (playerEntity.getPosX() < 0) {
                playerEntity.setPosX(0);
            // Right Boundary
            } else if (playerEntity.getPosX() + playerEntity.getWidth() > Gdx.graphics.getWidth()) {
                playerEntity.setPosX(Gdx.graphics.getWidth() - playerEntity.getWidth());
            // Bottom Boundary
            } else if (playerEntity.getPosY() < 0) {
                playerEntity.setPosY(0);
            // Top Boundary
            } else if (playerEntity.getPosY() + playerEntity.getHeight() > Gdx.graphics.getHeight()) {
                playerEntity.setPosY(Gdx.graphics.getHeight() - playerEntity.getHeight());
            }
        }
    };
}
