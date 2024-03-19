package com.mygdx.game.GameLayer.InputOutput;

import com.mygdx.game.EngineLayer.EngineInputOutputManager;

public class InputOutputManager extends EngineInputOutputManager {
    private Inputs preferredControls;
    private InputOutputFactory inputOutputFactory;

    // Default Constructor to initialise preferred control to keyboard by default
    public InputOutputManager() {
        this.inputOutputFactory = new InputOutputFactory();
        this.preferredControls = inputOutputFactory.generateInput("KEYBOARDMOUSE");
    }

    // Factory method for switching of inputs
    public void switchDevice(String deviceType) {
        preferredControls = inputOutputFactory.generateInput(deviceType);
    };

    // Get Preferred Controls
    public Inputs getPreferredControls() {
        return preferredControls;
    }

    // Set Preferred Controls
    public void setPreferredControls(Inputs preferredControls) {
        this.preferredControls = preferredControls;
    }

    // Get Input Output Factory
    public InputOutputFactory getInputOutputFactory() {
        return inputOutputFactory;
    }

}
