package com.mygdx.game.GameLayer.InputOutput;
import com.badlogic.gdx.Gdx;
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
    private int teleportKey;
    private int explodeKey;

    // Default Constructor for all key bindings
    protected KeyboardMouse() {
        this.leftKey = Input.Keys.LEFT;
        this.rightKey = Input.Keys.RIGHT;
        this.upKey = Input.Keys.UP;
        this.downKey = Input.Keys.DOWN;
        this.startKey = Input.Keys.ENTER;
        this.restartKey = Input.Keys.R;
        this.menuKey = Input.Keys.M;
        this.pauseKey = Input.Keys.P;
        this.teleportKey = Input.Keys.SHIFT_LEFT;
        this.explodeKey = Input.Keys.SPACE;
    }

    // Get Left Key
    public boolean getLeftKey() {
        return Gdx.input.isKeyPressed(leftKey);
    }

    // Set Left Key
    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    // Get Right Key
    public boolean getRightKey() {
        return Gdx.input.isKeyPressed(rightKey);
    }

    // Set Right Key
    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }

    // Get Up Key
    public boolean getUpKey() {
        return Gdx.input.isKeyPressed(upKey);
    }

    // Set Up Key
    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    // Get Down Key
    public boolean getDownKey() {
        return Gdx.input.isKeyPressed(downKey);
    }

    // Set Down Key
    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    // Get Start Key
    public boolean getStartKey() {
        return Gdx.input.isKeyJustPressed(startKey);
    }

    // Set Start Key
    public void setStartKey(int startKey) { this.startKey = startKey; }

    // Get Restart Key
    public boolean getRestartKey() { return Gdx.input.isKeyJustPressed(restartKey); }

    // Set Restart Key
    public void setRestartKey(int restartKey) {
        this.restartKey = restartKey;
    }

    // Get Menu Key
    public boolean getMenuKey() { return Gdx.input.isKeyJustPressed(menuKey); }

    // Set Menu Key
    public void setMenuKey(int menuKey) { this.menuKey = menuKey; }

    // Get Pause Key
    public boolean getPauseKey() { return Gdx.input.isKeyJustPressed(pauseKey); }

    // Set Pause Key
    public void setPauseKey(int pauseKey) { this.pauseKey = pauseKey; }

    // Get Teleport Key
    public boolean getTeleportKey() {
        return Gdx.input.isKeyJustPressed(teleportKey);
    }

    // Set Teleport Key
    public void setTeleportKey(int teleportKey) {
        this.teleportKey = teleportKey;
    }

    // Get Explode Key
    public boolean getExplodeKey() {
        return Gdx.input.isKeyJustPressed(explodeKey);
    }

    // Set Explode Key
    public void setExplodeKey(int explodeKey) {
        this.explodeKey = explodeKey;
    }
}
