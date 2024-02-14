package com.mygdx.game.InputOutput;

// Input Manager Class
public class InputOutputManager {
    // Declare Attributes
    private Inputs preferredControls;

    // Default Constructor to initialise preferred control to keyboard by default
    public InputOutputManager() {
        this.preferredControls = new KeyboardMouse();
    }

    // Switching of inputs
    public void switchDevice() {};

    // Get Preferred Controls
    public Inputs getPreferredControls() {
        return preferredControls;
    }

    // Set Preferred Controls
    public void setPreferredControls(Inputs preferredControls) {
        this.preferredControls = preferredControls;
    }
}
