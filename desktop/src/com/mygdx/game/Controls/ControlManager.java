package com.mygdx.game.Controls;

// Control Manager Class
public class ControlManager {
    // Declare Variables
    private PlayerControls playerControls;

    // Default Constructor to initialise children
    public ControlManager() {
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
