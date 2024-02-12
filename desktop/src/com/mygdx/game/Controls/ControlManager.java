package com.mygdx.game.Controls;

import com.mygdx.game.Scenes.EndScene;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.Scene;
import com.mygdx.game.Scenes.StartScene;

// Control Manager Class
public class ControlManager {
    // Declare Variables
    private PlayerControls playerControls;
    private StartScreenControls startSceenControls;
    private EndScreenControls endScreenControls;
    private Controls currentControls;

    // Default Constructor to initialise children
    public ControlManager() {
        this.playerControls = new PlayerControls();
        this.startSceenControls = new StartScreenControls();
        this.endScreenControls = new EndScreenControls();
    }

    public void mapControls(Scene scene) {
        if (scene instanceof StartScene) {
            setCurrentControls(startSceenControls);
        } else if (scene instanceof GameScene) {
            setCurrentControls(playerControls);
        } else if (scene instanceof EndScene) {
            setCurrentControls(endScreenControls);
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

    // Get Current Controls
    public Controls getCurrentControls() {
        return currentControls;
    }

    // Set Current Controls
    public void setCurrentControls(Controls control) {
        this.currentControls = control;
    }
}
