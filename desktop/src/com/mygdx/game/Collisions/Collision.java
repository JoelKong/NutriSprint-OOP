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
    public Collision() {}

    // Collision of Player with AI
    protected void collidePlayerAI(Map<String,List<GameEntity>> entityMap) {
        for (GameEntity playerEntity: entityMap.get("player")) {
            Player player = (Player) playerEntity;
            for (GameEntity aiEntity: entityMap.get("spawnables")) {
                AI ai = (AI) aiEntity;
                if (player.getHitbox().overlaps(ai.getHitbox())) {
                    ai.setPopFromScreen(true);
                }
            }
        }
    };

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
