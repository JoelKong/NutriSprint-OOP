package com.mygdx.game.Scenes;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<String, Scene> sceneMap;
    private Scene currentScene;

    public SceneManager() {
        this.sceneMap = new HashMap<>();
        this.currentScene = new StartScene();
        sceneMap.put("start", new StartScene());
        sceneMap.put("game", new GameScene());
        sceneMap.put("end", new EndScene());
    }

    // Load Screens that dont require entities
    public void loadScreen(Scene scene) {
        scene.render();
    }

    public void loadScreen(Scene scene, )

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
    }
}
