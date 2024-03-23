package com.mygdx.game.GameLayer.Scenes;
import com.mygdx.game.EngineLayer.EngineSceneManager;
import com.badlogic.gdx.Game;

// SceneManager class taking common methods from its engine
public class SceneManager extends EngineSceneManager {
    // Parameterized constructor containing the game controller passed in to us from Main and then giving it to the engine
    public SceneManager(Game gameController) {
        super(gameController);
    }

    // Initialize all types of scenes and start off with the start scene
    public void initializeScenes() {
        getSceneMap().put("start", new StartScene(this));
        getSceneMap().put("game", new GameScene(this));
        getSceneMap().put("end", new EndScene(this));
        getSceneMap().put("instruction", new InstructionScene(this));
        transitionScenes("start");
    }
}
