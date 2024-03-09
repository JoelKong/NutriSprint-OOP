package com.mygdx.game.EngineLayer.Collisions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Entity.Apple;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.Entity.Rock;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    // Collision Handling for props
    protected void handlePropCollisions(Map<String, List<GameEntity>> entityMap) {
        List<GameEntity> props = entityMap.get("props"); // Rocks and other props
        List<GameEntity> players = entityMap.get("player");
        List<GameEntity> ais = entityMap.get("ai");

        // Handle collision for AI entities
        for (GameEntity ai : ais) {
            for (GameEntity prop : props) {
                if (collisionDetected(ai, prop)) {
                    if (prop instanceof Rock) {
                        resolveRockCollision(ai, prop);
                    }
                }
            }
        }

        // Handle collision for Player entities
        for (GameEntity entity : players) {
            Player player = (Player) entity;
            Iterator<GameEntity> iterator = props.iterator();

            while (iterator.hasNext()) {
                GameEntity prop = iterator.next();
                if (collisionDetected(player, prop)) {
                    if (prop instanceof Rock) {
                        resolveRockCollision(player, prop);
                    } else if (prop instanceof Apple) {
                        iterator.remove();
                        player.setScore(player.getScore() + 1);
                    }
                }
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

    // Utility function for rock collision
    private void resolveRockCollision(GameEntity entity, GameEntity obstacle) {
        Rectangle entityHitbox = entity.getHitbox();
        Rectangle obstacleHitbox = obstacle.getHitbox();

        // Calculate the minimum distance to move horizontally and vertically to exit the collision
        float moveRight = obstacleHitbox.x + obstacleHitbox.width - entityHitbox.x;
        float moveLeft = entityHitbox.x + entityHitbox.width - obstacleHitbox.x;
        float moveUp = obstacleHitbox.y + obstacleHitbox.height - entityHitbox.y;
        float moveDown = entityHitbox.y + entityHitbox.height - obstacleHitbox.y;

        // Choose the direction with the minimum distance to resolve the collision
        float minMove = Math.min(Math.min(moveRight, moveLeft), Math.min(moveUp, moveDown));

        // Adjust the entity's position based on the minimum movement required
        if (minMove == moveRight) {
            entity.setPosX(entity.getPosX() + moveRight);
        } else if (minMove == moveLeft) {
            entity.setPosX(entity.getPosX() - moveLeft);
        } else if (minMove == moveUp) {
            entity.setPosY(entity.getPosY() + moveUp);
        } else if (minMove == moveDown) {
            entity.setPosY(entity.getPosY() - moveDown);
        }
    }
}
