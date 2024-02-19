package com.mygdx.game.Scenes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Levels.Levels;

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

    public void drawSceneObjects(SpriteBatch batch, EntityManager entityManager, Levels levelAssets) {
        batch.begin();
            // Start Scene
            if (currentScene == "start") {
                scene.renderTextAtScenePosition(batch, "Press 'Enter' to start.", "center");
            }

            // Game Scene
            if (scene instanceof GameScene) {
                // Draw Entities
                entityManager.drawEntities(batch);

                // Render game text
                scene.renderTextAtScenePosition(batch, levelAssets.getLevelTitle(), "topleft");
                scene.renderTextAtScenePosition(batch, "Press P to pause", "top");
            }

            // End Scene
            if (scene instanceof EndScene) {
                scene.renderTextAtScenePosition(batch,"Game ended, press 'R' to restart or 'M' to return to Main Menu", "center");
            }
        batch.end();
    }

    public void disposeScenes(SpriteBatch batch) {
        for (Scenes scene: sceneMap.values()) {
            scene.dispose(batch);
        }
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
