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
    private int pauseKey;
    private int teleportKey;
    private int explodeKey;

    // Default Constructor for all key bindings
    protected KeyboardMouse() {
        this.leftKey = Input.Keys.A;
        this.rightKey = Input.Keys.D;
        this.upKey = Input.Keys.W;
        this.downKey = Input.Keys.S;
        this.pauseKey = Input.Keys.ESCAPE;
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
