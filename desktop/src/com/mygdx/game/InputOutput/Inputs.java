package com.mygdx.game.InputOutput;

// Input interface where all kinds of input devices must have the following key bindings
public interface Inputs {
    int getLeftKey();
    void setLeftKey(int leftKey);
    int getRightKey();
    void setRightKey(int rightKey);
    int getUpKey();
    void setUpKey(int upKey);
    int getDownKey();
    void setDownKey(int downKey);
    int getStartKey();
    void setStartKey(int startKey);
    int getRestartKey();
    void setRestartKey(int restartKey);
    int getMenuKey();
    void setMenuKey(int menuKey);
}
