package com.mygdx.game.Entity;
import com.mygdx.game.InputOutput.Inputs;
import java.util.List;

// Control Manager Class
public class PlayerControlManager {
    // Declare Attributes
    private PlayerControls playerControls;

    // Default Constructor to initialise player controls
    public PlayerControlManager() {
        this.playerControls = new PlayerControls();
    }

    // Initialising of player controls
    public void initialisePlayerControls(Inputs commandInput, List<Player> playerList) {
        for (Player player: playerList) {
            player.playerMovement(commandInput, playerControls);
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
