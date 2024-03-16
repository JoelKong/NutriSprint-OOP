package com.mygdx.game.GameLayer.InputOutput;

// Input interface where all kinds of input devices must have the following key bindings
public interface Inputs {
    boolean getLeftKey();
    void setLeftKey(int leftKey);
    boolean getRightKey();
    void setRightKey(int rightKey);
    boolean getUpKey();
    void setUpKey(int upKey);
    boolean getDownKey();
    void setDownKey(int downKey);
    boolean getPauseKey();
    void setPauseKey(int pauseKey);
    boolean getTeleportKey();
    void setTeleportKey(int teleportKey);
    boolean getExplodeKey();
    void setExplodeKey(int explodeKey);
}
