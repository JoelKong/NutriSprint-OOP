package com.mygdx.game.Controls;

import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.StartScene;

// Control Manager Class
public class ControlManager {
    // Declare Variables
    private PlayerControls playerControls;
    private startSceneControls startSceenControls;

    // Default Constructor to initialise children
    public ControlManager() {
        this.playerControls = new PlayerControls();
        this.startSceenControls = new StartScreenControls();
    }

    public void mapControls(GameScene scene) {
        if (scene instanceof StartScene) {
            setCurrentControls(startSceenControls);
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
