package com.mygdx.game.Scenes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControls;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Levels.Levels;

// GameScene class inherited from scenes
public class GameScene extends Scenes {
    // Declare attributes
    private Levels sceneLevelAssets;

    // Parameterized constructor to initialise details of game scene
    public GameScene() {
        super(2, "game");
    }

    // Render game scene
    @Override
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                       Inputs preferredControls, PlayerControls playerControls, LevelManager levelManager) {

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Retrieve current level assets
        this.sceneLevelAssets = levelManager.retrieveLevelAssets();

        // Draw entities and render text
        batch.begin();
            entityManager.drawEntities(batch);
            renderTextAtScenePosition(batch, this.sceneLevelAssets.getLevelTitle(), "topleft");
            renderTextAtScenePosition(batch, "Press P to pause", "top");
        batch.end();

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            sceneManager.setPauseSceneState(!sceneManager.getPauseSceneState());
        }

        // If not paused, initialise all forms of behavior and movement
        if (!sceneManager.getPauseSceneState()) {
            entityManager.initialiseEntityMovement(preferredControls, playerControls);
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap().get("ai"));
            collisionManager.initializeCollisions(entityManager.getEntityMap().get("ai"), entityManager.getEntityMap().get("player"));
        }

        // Advance to next level or end game
        if (levelManager.levelCleared(entityManager.getEntityMap())) {
            if (levelManager.nextLevelExists()) {
                levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
                this.sceneLevelAssets = levelManager.retrieveLevelAssets();
                entityManager.initializeEntities(this.sceneLevelAssets);
            } else {
                sceneManager.setCurrentScene("end");
            }
        }

    }
}
