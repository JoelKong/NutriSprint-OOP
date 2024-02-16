package com.mygdx.game.InputOutput;
import com.badlogic.gdx.Input;

// KeyboardMouse Class
public class KeyboardMouse implements Inputs {
    // Declare Attributes
    private int leftKey;
    private int rightKey;
    private int upKey;
    private int downKey;
    private int startKey;
    private int restartKey;
    private int menuKey;
    private int pauseKey;

    // Default Constructor
    protected KeyboardMouse() {
        this.leftKey = Input.Keys.A;
        this.rightKey = Input.Keys.D;
        this.upKey = Input.Keys.W;
        this.downKey = Input.Keys.S;
        this.startKey = Input.Keys.ENTER;
        this.restartKey = Input.Keys.R;
        this.menuKey = Input.Keys.M;
        this.pauseKey = Input.Keys.P;
    }

    // Get Left Key
    public int getLeftKey() {
        return leftKey;
    }

    // Set Left Key
    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    // Get Right Key
    public int getRightKey() {
        return rightKey;
    }

    // Set Right Key
    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    // Get Up Key
    public int getUpKey() {
        return upKey;
    }

    // Set Up Key
    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    // Get Down Key
    public int getDownKey() {
        return downKey;
    }

    // Set Down Key
    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    // Get Start Key
    public int getStartKey() { return startKey; }

    // Set Start Key
    public void setStartKey(int startKey) { this.startKey = startKey; }

    // Get Restart Key
    public int getRestartKey() { return restartKey; }

    // Set Restart Key
    public void setRestartKey(int restartKey) {
        this.restartKey = restartKey;
    }

    // Get Menu Key
    public int getMenuKey() { return menuKey; }

    // Set Menu Key
    public void setMenuKey(int menuKey) { this.menuKey = menuKey; }

    // Get Pause Key
    public int getPauseKey() { return pauseKey; }

    // Set Pause Key
    public void setPauseKey(int pauseKey) { this.pauseKey = pauseKey; }
}
