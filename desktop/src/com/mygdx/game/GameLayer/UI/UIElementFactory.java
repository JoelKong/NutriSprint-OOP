package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

public class UIElementFactory {
    private enum ButtonType {
        START, QUIT, RESTART, MAINMENU
    }

    public WindowButton createButton(String buttonName, SceneManager sceneManager, SoundManager soundManager) {
        ButtonType buttonType = getButtonType(buttonName);
        switch (buttonType) {
            case START:
                return new WindowButton("Start Game", () -> {
                    soundManager.playSoundEffect("BUTTONCLICK");
                    soundManager.stopBackgroundMusic("MENU");
                    sceneManager.transitionScenes("game");
                });

            case QUIT:
                return new WindowButton("Quit Game", () -> {
                    Gdx.app.exit();
                });

            case RESTART:
                return new WindowButton("Restart from first level", () -> {
                    soundManager.playSoundEffect("BUTTONCLICK");
                    soundManager.stopBackgroundMusic("GAMEOVER");
                    sceneManager.transitionScenes("game");
                });

            case MAINMENU:
                return new WindowButton("Return to main menu", () -> {
                    soundManager.playSoundEffect("BUTTONCLICK");
                    soundManager.stopBackgroundMusic("GAMEOVER");
                    sceneManager.transitionScenes("start");
                });
            default:
                System.out.println("Warning: Unknown buttonType when creating a new button object.");
                return null;
        }

    }

    // Takes in string, returns Enum value
    private ButtonType getButtonType(String buttonString) {
        // Convert the provided string to uppercase to match enum values
        buttonString = buttonString.toUpperCase();

        // Check if the provided type matches any of the enum values
        try {
            return ButtonType.valueOf(buttonString);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided type is not a valid enum value
            System.out.println("Invalid Button type: " + buttonString);
            return null;
        }
    }
}
