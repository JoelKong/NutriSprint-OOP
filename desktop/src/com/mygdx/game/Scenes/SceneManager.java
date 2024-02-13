package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Variables
    private Map<String, Scenes> sceneMap;
    private Scenes currentScene;

    // Store all scenes in a hashmap on initialisation and initialise current scene to start
    public SceneManager() {
        this.sceneMap = new HashMap<>();
        sceneMap.put("start", new StartScene());
        sceneMap.put("game", new GameScene());
        sceneMap.put("end", new EndScene());
        this.currentScene = sceneMap.get("start");
    }

    // Load a specific scene
    public void loadScene(Scenes scene) {
        scene.render(Gdx.graphics.getDeltaTime());
    }

    // Get Current Scene
    public Scenes getCurrentScene() {
        return currentScene;
    }

    // Set Current Scene
    public void setCurrentScene(Scenes scene) {
        this.currentScene = scene;
    }

    // Get Scene Map
    public Map<String, Scenes> getSceneMap() {
        return sceneMap;
    }

    // Set Scene Map
    public void setSceneMap(Map<String, Scenes> sceneMap) {
        this.sceneMap = sceneMap;
    }
}
