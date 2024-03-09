package com.mygdx.game.EngineLayer.PlayerControls;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;

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
    protected void teleport(Player player, Inputs preferredInput) {
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
    }
}
