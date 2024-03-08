package com.mygdx.game.EngineLayer.Collisions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;

import java.util.List;

// Collision class for all kinds of collisions
public class Collision {
    // Default Constructor
    protected Collision() {}

    // Detects collisions between 2 GameEntities
    protected boolean collisionDetected(GameEntity A, GameEntity B) {
        return A.getHitbox().overlaps(B.getHitbox());
    }

    // Collision handling for AI
    protected void handleAICollision(List<GameEntity> aiEntities, Player player) {
        // Check for collision between AI and other AI
        for (GameEntity ai : aiEntities) {
            Vector2 avoidance = new Vector2();

            // Check for collision between AI and other AI for avoidance
            for (GameEntity otherAi : aiEntities) {
                if (ai != otherAi && collisionDetected(ai, otherAi)) {
                    Vector2 between = new Vector2(ai.getPosX() - otherAi.getPosX(), ai.getPosY() - otherAi.getPosY());
                    if (between.len() < 32) {
                        avoidance.add(between.nor());
                    }
                }
            }

            // Apply avoidance if needed
            if (!avoidance.isZero()) {
                avoidance.scl(30);
                ai.setPosX(ai.getPosX() + avoidance.x * Gdx.graphics.getDeltaTime());
                ai.setPosY(ai.getPosY() + avoidance.y * Gdx.graphics.getDeltaTime());
                gameWindowCollision(ai);
            }

            // Check for collision between AI and player
            if (collisionDetected(ai, player)) {
                aiEntities.remove(ai);
                player.setHealth(player.getHealth() - 1);
                break;
            }
        }
    }

    // Collision of entity with game window
    protected void gameWindowCollision (GameEntity entity) {
        // Left Boundary
        if (entity.getPosX() < 0) {
            entity.setPosX(0);
        // Right Boundary
        } else if (entity.getPosX() + entity.getWidth() > Gdx.graphics.getWidth()) {
            entity.setPosX(Gdx.graphics.getWidth() - entity.getWidth());
        // Bottom Boundary
        } else if (entity.getPosY() < 0) {
            entity.setPosY(0);
        // Top Boundary
        } else if (entity.getPosY() + entity.getHeight() > Gdx.graphics.getHeight() - 30) {
            entity.setPosY(Gdx.graphics.getHeight() - 30 - entity.getHeight());
        }

    }
}
