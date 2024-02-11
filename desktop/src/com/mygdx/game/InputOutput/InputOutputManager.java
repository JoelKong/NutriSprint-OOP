package com.mygdx.game.InputOutput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// Input Manager Class
public class InputOutputManager {
    // Declare Variables
    private KeyboardMouse keyboardMouse;

    // Default Constructor to initialise types of controls
    public InputOutputManager() {
        this.keyboardMouse = new KeyboardMouse();
    }

    // Switching of inputs
    public void switchDevice() {};

    // Update Keys
    public void updateKeys() {
        keyboardMouse.setLeftKey(Gdx.input.isKeyPressed(Input.Keys.A));
        keyboardMouse.setRightKey(Gdx.input.isKeyPressed(Input.Keys.D));
        keyboardMouse.setUpKey(Gdx.input.isKeyPressed(Input.Keys.W));
        keyboardMouse.setDownKey(Gdx.input.isKeyPressed(Input.Keys.S));
    }

    // Get KeyboardMouse Input
    public KeyboardMouse getKeyboardMouse() {
        return keyboardMouse;
    }

    // Set KeyboardMouse Input
    public void setKeyboardMouse(KeyboardMouse keyboardMouse) {
        this.keyboardMouse = keyboardMouse;
    }
}
