package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLayer.Collisions.CollisionManager;
import com.mygdx.game.GameLayer.Entity.PlayerControlManager;
import com.mygdx.game.GameLayer.Entity.PlayerControls;
import com.mygdx.game.GameLayer.Entity.AIControlManager;
import com.mygdx.game.GameLayer.Entity.EntityManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.LevelManager;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.UI.UiManager;

// GameScene class inherited from scenes
public class GameScene extends Scenes {
    // Declare attributes
    private SceneManager sceneManager;
    private Game gameController;
    private Levels sceneLevelAssets;
    private boolean pauseSceneState;
    private LevelManager levelManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private UiManager uiManager;

    // Parameterized constructor to initialise details of game scene
    protected GameScene(SceneManager sceneManager) {
        super(2, "game");
        this.sceneManager = sceneManager;
        this.gameController = sceneManager.getGameController();;
        this.pauseSceneState = false;
        this.levelManager = new LevelManager();
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AIControlManager();
        this.collisionManager = new CollisionManager();
        this.entityManager = new EntityManager();
    }

    // Check if level assets available for selected level, if not game ends
    @Override
    public void show() {
        sceneLevelAssets = levelManager.retrieveLevelAssets();

        if (sceneLevelAssets == null) {
            levelManager.setLevelNumber(1);
            gameController.setScreen(sceneManager.getSceneMap().get("end"));
        } else {
            try {
                setSceneBackgroundTexture(new Texture(Gdx.files.internal("droplet.png")));
                uiManager = new UiManager(sceneManager.getBatch(), getCamera().getUiViewport());
                uiManager.startGameHUD();
                entityManager.initializeEntities(sceneLevelAssets);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Render game screen
    @Override
    public void render(float delta) {
        // Get necessary data
        PlayerControls playerControls = playerControlManager.getPlayerControls();
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        SpriteBatch batch = sceneManager.getBatch();

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Focus the camera on our player
        getCamera().focusCamera(entityManager.getPlayersList().get(0).getPosX(), entityManager.getPlayersList().get(0).getPosY(), batch);

        // Draw our game scene
        drawScene(batch, getSceneBackgroundTexture(), entityManager);

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseSceneState = !pauseSceneState;
        }

        // If not paused, initialise all forms of behavior and movement
        if (!pauseSceneState) {
            entityManager.initialiseEntityMovement(preferredControls, playerControls);
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap().get("ai"));
            collisionManager.initializeCollisions(entityManager.getEntityMap().get("ai"), entityManager.getEntityMap().get("player"));
        }

        // Advance to next level or end game
        if (levelManager.levelCleared(entityManager.getEntityMap())) {
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            gameController.setScreen(this);
        }

        // Render HUD
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }

    // Upon switching screens dispose all stuff on the screen
    public void dispose() {
        entityManager.disposeEntities();
    };

    // Get Level Manager
    public LevelManager getLevelManager() {
        return levelManager;
    }

    // Get Player Control Managers
    public PlayerControlManager getPlayerControlManager() {
        return playerControlManager;
    }

    // Get AI Control Manager
    public AIControlManager getAiControlManager() {
        return aiControlManager;
    }

    // Get Collision Manager
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    // Get Entity Manager
    public EntityManager getEntityManager() {
        return entityManager;
    }

    // Get UI manager
    public UiManager uiManager() {
        return uiManager;
    }

    // Get Pause Scene State
    public boolean isPauseSceneState() {
        return pauseSceneState;
    }

    // Set Pause Scene State
    public void setPauseSceneState(boolean pauseSceneState) {
        this.pauseSceneState = pauseSceneState;
    }
}
