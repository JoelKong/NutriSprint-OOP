package com.mygdx.game.EngineLayer.PlayerControls;

import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;

// Control Manager Class
public class PlayerControlManager {
    // Declare Attributes
    private PlayerControls playerControls;

    // Default Constructor to initialise player controls
    public PlayerControlManager() {
        this.playerControls = new PlayerControls();
    }

    // Manage Player Controls
    public void manageControls(String command, Player player, Inputs preferredInput) {
        switch (command) {
            case "UP":
                playerControls.moveUp(player);
                break;
            case "DOWN":
                playerControls.moveDown(player);
                break;
            case "LEFT":
                playerControls.moveLeft(player);
                break;
            case "RIGHT":
                playerControls.moveRight(player);
                break;
            case "TELEPORT":
                playerControls.teleport(player, preferredInput);
            default:
        }
    }

    // Get Player Controls
    public PlayerControls getPlayerControls() {
        return playerControls;
    }

    // Set Player Controls
    public void setPlayerControls(PlayerControls playerControls) {
        this.playerControls = playerControls;
    }
}
