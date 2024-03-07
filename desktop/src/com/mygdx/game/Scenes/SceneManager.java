package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Attributes
    private Map<String, Screen> sceneMap;
    private Main gameController;

    // Store all scenes in a hashmap on initialisation and initialise current scene to start scene
    public SceneManager(Main gameController) {
        this.sceneMap = new HashMap<>();
        this.gameController = gameController;
        sceneMap.put("start", new StartScene(gameController));
        sceneMap.put("game", new GameScene(gameController));
        sceneMap.put("end", new EndScene(gameController));
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
}
