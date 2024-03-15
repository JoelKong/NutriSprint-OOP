package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Game;
import com.mygdx.game.EngineLayer.EngineSceneManager;

// SceneManager class
public class SceneManager extends EngineSceneManager {

    public SceneManager(Game gameController) {
        super(gameController);
    }

    // Load a scene starting with the start scene
    public void initializeScenes() {
        sceneMap.put("start", new StartScene(this));
        sceneMap.put("game", new GameScene(this));
        sceneMap.put("end", new EndScene(this));
        gameController.setScreen(sceneMap.get("start"));
    }
}
