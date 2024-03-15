package com.mygdx.game.EngineLayer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;
import java.util.Map;

public abstract class EngineSceneManager {
    protected SpriteBatch batch;
    protected Map<String, Screen> sceneMap;
    protected Game gameController;

    public EngineSceneManager(Game gameController) {
        this.gameController = gameController;
        this.batch = new SpriteBatch(); // Singleton Spritebatch
        this.sceneMap = new HashMap<>();
    }

    // Abstract method to initialize scenes.
    // SceneManager subclass will provide implementation.
    public abstract void initializeScenes();

    // Concrete methods that subclasses can use without overriding
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
