package com.mygdx.game.GameLayer.InputOutput;

import com.mygdx.game.EngineLayer.EngineInputOutputManager;

// Input Output Manager Class
public class InputOutputManager extends EngineInputOutputManager {
    // Declare Attributes
    private Inputs preferredControls;

    // Default Constructor to initialise preferred control to keyboard by default
    public InputOutputManager() {
        this.preferredControls = new KeyboardMouse();
    }

    // Factory method for switching of inputs
    public void switchDevice(String deviceType) {
        if ("CONTROLLER".equals(deviceType)) {
            // preferredControls = new Controller(); proof of concept for switching devices
        }
    };

    // Get Preferred Controls
    public Inputs getPreferredControls() {
        return preferredControls;
    }

    // Set Preferred Controls
    public void setPreferredControls(Inputs preferredControls) {
        this.preferredControls = preferredControls;
    }
}
