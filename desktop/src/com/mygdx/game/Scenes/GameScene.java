package com.mygdx.game.Scenes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.PlayerControls;
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

// GameScene class inherited from scenes
public class GameScene extends Scenes {
    // Declare attributes
    private Levels sceneLevelAssets;

    // Parameterized constructor to initialise details of game scene
    public GameScene() {
        super(2, "game");
    }

    // Generic function to check completion of level FOR NOW since no game specifics yet
    public boolean levelCleared(Map<String, List<GameEntity>> entityMap) {
        for (GameEntity aiEntity: entityMap.get("spawnables")) {
            AI ai = (AI) aiEntity;
            if (!ai.getPopFromScreen()) {
                return false;
            }
        }
        return true;
    }

    // Render game scene
    @Override
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                       InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {
        // Get user device and controls
        Inputs preferredControls = inputOutputManager.getPreferredControls();
        PlayerControls playerControls = playerControlManager.getPlayerControls();

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Retrieve current level assets
        this.sceneLevelAssets = levelManager.retrieveLevelAssets(levelManager.getLevelNumber());

        // Draw entities and render text
        batch.begin();
            entityManager.drawEntities(batch);
            renderTextAtScenePosition(batch, this.sceneLevelAssets.getLevelTitle(), "topleft");
            renderTextAtScenePosition(batch, "Press P to pause", "top");
        batch.end();

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseState = !pauseState;
        }

        // If not paused, initialise all forms of behavior and movement
        if (!pauseState) {
            playerControlManager.initialisePlayerControls();
            entityManager.initializePlayerMovement(preferredControls, playerControls);
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap());
            collisionManager.initializeCollisions(entityManager.getEntityMap());
        }

        // Advance to next level or end game
        if (levelCleared(entityManager.getEntityMap())) {
            if (levelManager.nextLevelExists()) {
                levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
                this.levelAssets = levelManager.retrieveCurrentLevelAssets();
                entityManager.initializeEntities(this.levelAssets);
            } else {
                sceneManager.setCurrentScene("end");
            }
        }

    }
}
