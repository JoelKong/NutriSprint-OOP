package com.mygdx.game.Controls;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Entity.Player;


// Player Controls Class
public class PlayerControls {
    // Default Constructor for player control
    protected PlayerControls() {}

    // Move Up Command
    public void moveUp(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosY(player.getPosY() + moveAmount);
    }

    // Move Down Command
    public void moveDown(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosY(player.getPosY() - moveAmount);
    }

    // Move Left Command
    public void moveLeft(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosX(player.getPosX() - moveAmount);
    }

    // Move Right Command
    public void moveRight(Player player) {
        float moveAmount = player.getSpeed() * Gdx.graphics.getDeltaTime();
        player.setPosX(player.getPosX() + moveAmount);
    }
}
