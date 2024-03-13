package com.mygdx.game.EngineLayer.Entity;

// Control Manager Class
public class PlayerControlManager {
    // Declare Attributes
    private PlayerControls playerControls;

    // Default Constructor to initialise player controls
    public PlayerControlManager() {
        this.playerControls = new PlayerControls();
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
