package com.mygdx.game.Collisions;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entity.GameEntity;

// Collision class for all kinds of collisions
public class Collision {
    // Default Constructor
    protected Collision() {}

    // Detects collisions between 2 GameEntities
    protected boolean collisionDetected(GameEntity A, GameEntity B) {
        return A.getHitbox().overlaps(B.getHitbox());
    }

    // Collision of entity with game window
    protected void gameWindowCollision (GameEntity player) {
        // Left Boundary
        if (player.getPosX() < 0) {
            player.setPosX(0);
        // Right Boundary
        } else if (player.getPosX() + player.getWidth() > Gdx.graphics.getWidth()) {
            player.setPosX(Gdx.graphics.getWidth() - player.getWidth());
        // Bottom Boundary
        } else if (player.getPosY() < 0) {
            player.setPosY(0);
        // Top Boundary
        } else if (player.getPosY() + player.getHeight() > Gdx.graphics.getHeight()) {
            player.setPosY(Gdx.graphics.getHeight() - player.getHeight());
        }

    }
}
