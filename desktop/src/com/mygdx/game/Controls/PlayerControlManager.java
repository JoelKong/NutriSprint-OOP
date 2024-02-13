package com.mygdx.game.Controls;

// Control Manager Class
public class PlayerControlManager {
    // Declare Variables
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
