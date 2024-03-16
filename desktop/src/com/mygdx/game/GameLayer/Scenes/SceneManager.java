package com.mygdx.game.GameLayer.Scenes;
import com.mygdx.game.EngineLayer.EngineSceneManager;
import com.badlogic.gdx.Game;

// SceneManager class
public class SceneManager extends EngineSceneManager {
    // Parameterized constructor containing the game controller passed in to us from Main
    public SceneManager(Game gameController) {
        super(gameController);
    }

    // Load a scene starting with the start scene
    public void initializeScenes() {
        getSceneMap().put("start", new StartScene(this));
        getSceneMap().put("game", new GameScene(this));
        getSceneMap().put("end", new EndScene(this));
        transitionScenes("start");
    }
}
