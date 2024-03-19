package com.mygdx.game.GameLayer.InputOutput;

public class InputOutputFactory {
    // Get user requested input type (when plugging in a controller or setting an option)
    public Inputs generateInput(String inputType) {
        switch (inputType) {
            case "KEYBOARDMOUSE":
                return new KeyboardMouse();
//            case "CONTROLLER":
//                return new Controller(); proof of concept
            default:
                return new KeyboardMouse();
        }
    }
}
