package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Attributes
    private SpriteBatch batch;
    private Map<String, Screen> sceneMap;
    private Game gameController;

    // Parameterized constructor containing the game controller
    public SceneManager(Game gameController) {
        this.sceneMap = new HashMap<>();
        this.gameController = gameController;
        this.batch = new SpriteBatch(); // Singleton Spritebatch
        sceneMap.put("start", new StartScene(this));
        sceneMap.put("game", new GameScene(this));
        sceneMap.put("end", new EndScene(this));
    }

    // Load a scene starting with the start scene
    public void initializeScenes() {
        gameController.setScreen(sceneMap.get("start"));
    }

    // Get Scene Map
    public Map<String, Screen> getSceneMap() {
        return sceneMap;
    }

    // Set Scene Map
    public void setSceneMap(Map<String, Screen> sceneMap) {
        this.sceneMap = sceneMap;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Game getGameController() {
        return gameController;
    }

}
