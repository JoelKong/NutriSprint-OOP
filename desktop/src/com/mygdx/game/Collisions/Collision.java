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
    protected boolean CollisionDetected(GameEntity A, GameEntity B) {
        if (A.getHitbox().overlaps(B.getHitbox())) {
            return true;
        } else return false;
    }

    // Collision of entity with game window
    protected void GameWindowCollision (GameEntity player) {
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
