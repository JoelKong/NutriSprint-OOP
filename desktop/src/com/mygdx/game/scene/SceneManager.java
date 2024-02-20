package com.mygdx.game.scene;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.collision.CollisionManager;
import com.mygdx.game.entity.AIControlManager;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.PlayerControls;
import com.mygdx.game.io.Inputs;
import com.mygdx.game.level.LevelManager;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Attributes
    private Map<String, Scenes> sceneMap;
    private String currentScene;
    private boolean pauseSceneState;

    // Store all scenes in a hashmap on initialisation and initialise current scene to start scene
    public SceneManager() {
        this.sceneMap = new HashMap<>();
        sceneMap.put("start", new StartScene());
        sceneMap.put("game", new GameScene());
        sceneMap.put("end", new EndScene());
        this.currentScene = "start";
    }

    // Load a scene starting with the start scene
    public void initializeScenes(SpriteBatch batch, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                 Inputs preferredControls, PlayerControls playerControls, LevelManager levelManager) {

        Scenes scene = sceneMap.get(currentScene);
        if (scene instanceof StartScene || scene instanceof EndScene) {
            scene.render(this, batch, entityManager, preferredControls, levelManager);
        } else {
            scene.render(this, batch, entityManager, collisionManager, aiControlManager, preferredControls, playerControls, levelManager);
        }
    }

    public void disposeScenes(SpriteBatch batch) {
        for (Scenes scene: sceneMap.values()) {
            scene.dispose(batch);
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

    // Get Pause Scene State
    public boolean getPauseSceneState() {
        return pauseSceneState;
    }

    // Set Pause Scene State
    public void setPauseSceneState(boolean pauseSceneState) {
        this.pauseSceneState = pauseSceneState;
    }
}
