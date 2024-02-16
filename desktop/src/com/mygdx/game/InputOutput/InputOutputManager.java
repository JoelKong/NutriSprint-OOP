package com.mygdx.game.InputOutput;

// Input Manager Class
public class InputOutputManager {
    // Declare Attributes
    private Inputs preferredControls;
    private KeyboardMouse keyboardMouse;

    // Default Constructor to initialise preferred control to keyboard by default
    public InputOutputManager() {
        this.keyboardMouse = new KeyboardMouse();
        this.preferredControls = keyboardMouse;
    }

    // Switching of inputs
    public void switchDevice(String deviceType) {
        if ("controller".equals(deviceType)) {
            // preferredControls = controller;        proof of concept for switching devices
        }
    };

    // Get KeyboardMouse
    public KeyboardMouse getKeyboardMouse() {
        return keyboardMouse;
    }

    // Set KeyboardMouse
    public void setKeyboardMouse(KeyboardMouse keyboardMouse) {
        this.keyboardMouse = keyboardMouse;
    }

    // Get Preferred Controls
    public Inputs getPreferredControls() {
        return preferredControls;
    }

    // Set Preferred Controls
    public void setPreferredControls(Inputs preferredControls) {
        this.preferredControls = preferredControls;
    }
}
