package com.mygdx.game.EngineLayer.PlayerControls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.EngineLayer.Effects.Effect;
import com.mygdx.game.EngineLayer.Effects.EffectManager;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Player Controls Class
public class PlayerControls {
    // Default Constructor for player control
    protected PlayerControls() {}

    // Move Up Command
    protected void moveUp(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosY(player.getPosY() + moveAmount);
    }

    // Move Down Command
    protected void moveDown(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosY(player.getPosY() - moveAmount);
    }

    // Move Left Command
    protected void moveLeft(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosX(player.getPosX() - moveAmount);
    }

    // Move Right Command
    protected void moveRight(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosX(player.getPosX() + moveAmount);
    }

    // Teleport command
    protected void teleport(Player player, Inputs preferredInput, EffectManager effectManager) { // need gameTime in gamestatemanager
        long timeSinceLastTeleport = TimeUtils.millis() - player.getLastTeleportTime();
        if (timeSinceLastTeleport < player.getTeleportCooldown()) return;

        Vector2 teleportDirection = new Vector2();
        if (preferredInput.getUpKey()) teleportDirection.y = 1;
        if (preferredInput.getDownKey()) teleportDirection.y = -1;
        if (preferredInput.getLeftKey()) teleportDirection.x = -1;
        if (preferredInput.getRightKey()) teleportDirection.x = 1;

        if (!teleportDirection.isZero()) {
            teleportDirection.nor().scl(player.getTeleportDistance());
            player.setPosX(player.getPosX() + teleportDirection.x);
            player.setPosY(player.getPosY() + teleportDirection.y);
            player.setLastTeleportTime(TimeUtils.millis());
        }

        // Create the teleport effect at the new position
        Texture teleportEffectTexture = new Texture(Gdx.files.internal("Effects/teleport.png"));
        Effect teleportEffect = new Effect(new Vector2(player.getPosX(), player.getPosY()), teleportEffectTexture, 50, 50, 0.5f);
        effectManager.addEffect(teleportEffect);
    }

    // Explosion around player
    protected void triggerExplosion(Player player, Map<String, List<GameEntity>> entityMap, EffectManager effectManager) {
        final float explosionRadius = 200;
        Circle explosionArea = new Circle(player.getPosX() + player.getWidth() / 2,
                player.getPosY() + player.getHeight() / 2,
                explosionRadius);

        // Iterate over all entities in the map
        for (List<GameEntity> entities : entityMap.values()) {
            Iterator<GameEntity> iterator = entities.iterator();

            while (iterator.hasNext()) {
                GameEntity entity = iterator.next();

                // Calculate center position of the entity
                Vector2 entityCenter = new Vector2(entity.getPosX() + entity.getWidth() / 2,
                        entity.getPosY() + entity.getHeight() / 2);

                // If entity is within the explosion area and is not the player, remove it
                if (explosionArea.contains(entityCenter) && entity != player) {
                    iterator.remove();
                }
            }
        }

        // Create the explosion effect at player position
        Texture explosionEffectTexture = new Texture(Gdx.files.internal("Effects/explosion.png"));
        Effect explosionEffect = new Effect(new Vector2(player.getPosX(), player.getPosY()), explosionEffectTexture, 120, 120, 0.5f);
        effectManager.addEffect(explosionEffect);
    }
}
