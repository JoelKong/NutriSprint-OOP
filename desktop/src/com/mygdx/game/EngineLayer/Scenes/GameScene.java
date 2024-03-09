package com.mygdx.game.EngineLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.EngineLayer.Collisions.CollisionManager;
import com.mygdx.game.EngineLayer.Entity.AIControlManager;
import com.mygdx.game.EngineLayer.Entity.EntityManager;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.Levels.LevelManager;
import com.mygdx.game.EngineLayer.Levels.Levels;
import com.mygdx.game.Main;
import com.mygdx.game.EngineLayer.UI.UiManager;

// GameScene class inherited from scenes
public class GameScene extends Scenes {
    // Declare attributes
    private Levels sceneLevelAssets;
    private float timeSinceLastSpawn;
    private boolean pauseSceneState;
    private LevelManager levelManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private UiManager uiManager;

    // Parameterized constructor to initialise details of game scene
    protected GameScene(Main gameController) {
        super(2, "game", gameController);
        this.pauseSceneState = false;
        this.timeSinceLastSpawn = 0f;
        this.levelManager = new LevelManager();
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
            getGameController().setScreen(getGameController().getSceneManager().getSceneMap().get("end"));
        } else {
            try {
                setSceneBackgroundTexture(new Texture(Gdx.files.internal("Scenes/gamescene.png")));
                uiManager = new UiManager(getGameController().getBatch(), getCamera().getUiViewport());
                uiManager.startGameHUD();
                uiManager.updateGameHUDLevel(levelManager.getLevelNumber());
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
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        SpriteBatch batch = getGameController().getBatch();
        timeSinceLastSpawn += 1;

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Focus the camera on our player
        getCamera().focusCamera(entityManager.getPlayersList().get(0).getPosX(), entityManager.getPlayersList().get(0).getPosY(), batch);

        // Draw our game scene
        drawScene(batch, getSceneBackgroundTexture(), entityManager);

        // Keep spawning entities on the screen after each interval
        if (timeSinceLastSpawn >= 100f) {
            timeSinceLastSpawn = 0f;
            for (String entityType: sceneLevelAssets.getRespawnables()) {
                GameEntity entity = entityManager.createEntity(entityManager.getEntityType(entityType), sceneLevelAssets);
                entityManager.getAiEntityList().add(entity);
            }
        }

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseSceneState = !pauseSceneState;
        }

        // If not paused, initialise all forms of behavior and movement
        if (!pauseSceneState) {
            entityManager.initialiseEntityMovement(preferredControls);
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap().get("ai"), entityManager.getEntityMap().get("player").get(0));
            collisionManager.initializeCollisions(entityManager.getEntityMap());
        }

        // Updating of player entity status
        entityManager.checkPlayerEntityStatus(sceneLevelAssets.getScoreNeeded());

        // Advance to next level or end game
        if (levelManager.levelCleared(entityManager.getPlayersList())) {
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            getGameController().setScreen(this);
        }

        // End game if player loses
        for (GameEntity entity: entityManager.getPlayersList()) {
            Player player = (Player) entity;
            if (player.getLoseStatus()) {
                levelManager.setLevelNumber(1);
                getGameController().setScreen(getGameController().getSceneManager().getSceneMap().get("end"));
            }
        }

        // Render HUD
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();

        Gdx.app.log("Level Number", String.valueOf(levelManager.getLevelNumber()));
    }

    // Upon switching screens dispose all stuff on the screen
    public void dispose() {
        entityManager.disposeEntities();
    };

    // Get time since last spawn
    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    // Set time since last spawn
    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }

    // Get Level Manager
    public LevelManager getLevelManager() {
        return levelManager;
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
