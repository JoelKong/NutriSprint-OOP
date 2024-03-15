package com.mygdx.game.EngineLayer;

import com.mygdx.game.GameLayer.InputOutput.Inputs;

public abstract class EngineInputOutputManager {
    // Factory method for switching of inputs
    public void switchDevice(String deviceType) {
        if ("CONTROLLER".equals(deviceType)) {
            // preferredControls = new Controller(); proof of concept for switching devices
        }
    };
}
