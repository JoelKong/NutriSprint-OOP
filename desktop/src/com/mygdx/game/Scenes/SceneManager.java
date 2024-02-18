package com.mygdx.game.Scenes;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Attributes
    private Map<String, Scenes> sceneMap;
    private String currentScene;

    // Store all scenes in a hashmap on initialisation and initialise current scene to start scene
    public SceneManager() {
        this.sceneMap = new HashMap<>();
        sceneMap.put("start", new StartScene());
        sceneMap.put("game", new GameScene());
        sceneMap.put("end", new EndScene());
        this.currentScene = "start";
    }

    // Load a scene starting with the start scene
    public void initializeScenes(EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {

        Scenes scene = sceneMap.get(currentScene);
        if (scene instanceof StartScene || scene instanceof EndScene) {
            scene.render(this, entityManager, inputOutputManager, levelManager);
        } else {
            scene.render(this, entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
        }

    }

    // Get Scene Map
    public Map<String, Scenes> getSceneMap() {
        return sceneMap;
    }

    // Set Scene Map
    public void setSceneMap(Map<String, Scenes> sceneMap) {
        this.sceneMap = sceneMap;
    }

    // Get Current Scene
    public String getCurrentScene() {
        return currentScene;
    }

    // Set Current Scene
    public void setCurrentScene(String scene) {
        this.currentScene = scene;
    }
}
