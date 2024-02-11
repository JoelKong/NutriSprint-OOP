package com.mygdx.game.InputOutput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// KeyboardMouse Class
public class KeyboardMouse {
    // Declare Variables
    private boolean leftKey;
    private boolean rightKey;
    private boolean upKey;
    private boolean downKey;

    // Default Constructor
    protected KeyboardMouse() {
        this.leftKey = Gdx.input.isKeyPressed(Input.Keys.A);
        this.rightKey = Gdx.input.isKeyPressed(Input.Keys.D);
        this.upKey = Gdx.input.isKeyPressed(Input.Keys.W);
        this.downKey = Gdx.input.isKeyPressed(Input.Keys.S);
    }

    // Parameterized Constructor
    protected KeyboardMouse(boolean left, boolean right, boolean up, boolean down) {
        this.leftKey = left;
        this.rightKey = right;
        this.upKey = up;
        this.downKey = down;
    }

    // Get Left Key
    public boolean getLeftKey() {
        return leftKey;
    }

    // Set Left Key
    public void setLeftKey(boolean leftKey) {
        this.leftKey = leftKey;
    }

    // Get Right Key
    public boolean getRightKey() {
        return rightKey;
    }

    // Set Right Key
    public void setRightKey(boolean rightKey) {
        this.rightKey = rightKey;
    }

    // Get Up Key
    public boolean getUpKey() {
        return upKey;
    }

    // Set Up Key
    public void setUpKey(boolean upKey) {
        this.upKey = upKey;
    }

    // Get Down Key
    public boolean getDownKey() {
        return downKey;
    }

    // Set Down Key
    public void setDownKey(boolean downKey) {
        this.downKey = downKey;
    }
}
