package com.mygdx.game.EngineLayer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;

// Every scene engine should contain a batch, a map of scenes and also a gamecontroller to switch scenes, will be singleton together will scenemanager
public abstract class EngineSceneManager {
    private SpriteBatch batch;
    private Map<String, Screen> sceneMap;
    private Game gameController;

    public EngineSceneManager(Game gameController) {
        this.gameController = gameController;
        this.batch = new SpriteBatch();
        this.sceneMap = new HashMap<>();
    }

    // Abstract method to initialize scenes.
    // SceneManager subclass will provide implementation.
    public void initializeScenes() {};

    // Every scene engine should have the ability to transition scenes based off what scene we want
    public void transitionScenes(String scene) {
        gameController.setScreen(sceneMap.get(scene));
    }

    // Necessary getters or setters
    public Game getGameController() {
        return gameController;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Map<String, Screen> getSceneMap() {
        return sceneMap;
    }

    public void setSceneMap(Map<String, Screen> sceneMap) {
        this.sceneMap = sceneMap;
    }

}
