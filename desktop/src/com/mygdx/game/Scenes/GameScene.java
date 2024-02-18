package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Controls.PlayerControls;
import com.mygdx.game.Entity.AI;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.GameEntity;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Levels.Levels;

import java.util.List;
import java.util.Map;

public class GameScene extends Scenes implements Screen {
    private SpriteBatch batch;
    private boolean pauseState;
    private Levels levelAssets;

    public GameScene() {
        super(2, "game");
        this.batch = new SpriteBatch();
        this.pauseState = false;
    }

    public boolean victoryCondition(Map<String, List<GameEntity>> entityMap) {
        for (GameEntity aiEntity: entityMap.get("spawnables")) {
            AI ai = (AI) aiEntity;
            if (!ai.getPopFromScreen()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(SceneManager sceneManager, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                       InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {

        // Get user device and controls
        Inputs preferredControls = inputOutputManager.getPreferredControls();
        PlayerControls playerControls = playerControlManager.getPlayerControls();

        ScreenUtils.clear(0, 0, 0.2f, 1);

        // renderTextAtTop(batch, "Press 'P' to pause.");
        this.levelAssets = levelManager.retrieveCurrentLevelAssets();
        renderLevelTitleText(batch, this.levelAssets.getLevelTitle());

        // Draw entities and text
        batch.begin();
            entityManager.drawEntities(batch);
        batch.end();

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseState = !pauseState;
        }

        // If not paused, initialise all forms of behavior and movement
        if (!pauseState) {
            entityManager.initializePlayerMovement(preferredControls, playerControls);
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap());
            collisionManager.initializeCollisions(entityManager.getEntityMap());
        }

        // Advance to next level or end condition
        if (victoryCondition(entityManager.getEntityMap())) {
            if (levelManager.doesNextLevelExist()) {
                levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
                this.levelAssets = levelManager.retrieveCurrentLevelAssets();
                entityManager.initializeEntities(this.levelAssets);
            } else {
                sceneManager.setCurrentScene("end");
            }
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

//    public void logEntityPositions(EntityManager entityManager) {
//        Map<String, List<GameEntity>> entityMap = entityManager.getEntityMap();
//        for (Map.Entry<String, List<GameEntity>> entry : entityMap.entrySet()) {
//            for (GameEntity entity : entry.getValue()) {
//                // Adjusted to directly use hypothetical getX() and getY() methods
//                // Replace these with the actual methods or fields used in your Entity class
//                float x = entity.getPosX(); // Assuming an getX() method exists
//                float y = entity.getPosY(); // Assuming a getY() method exists
//
//                Gdx.app.log("EntityPosition", "Entity: " + entity.getClass().getSimpleName() + " at x: " + x + " y: " + y);
//            }
//        }
//    }
//
//    public void logCameraView(OrthographicCamera camera) {
//        Gdx.app.log("CameraView", "Camera position: x=" + camera.position.x + " y=" + camera.position.y);
//        Gdx.app.log("CameraView", "Viewport dimensions: width=" + camera.viewportWidth + " height=" + camera.viewportHeight);
//  }
}
