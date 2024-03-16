package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLayer.Collisions.CollisionManager;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.Entity.AIControlManager;
import com.mygdx.game.GameLayer.Entity.EntityManager;
import com.mygdx.game.GameLayer.Entity.Player;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.LevelManager;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.UI.UiManager;

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
    private EffectManager effectManager;
    private SceneManager sceneManager;
    private UiManager uiManager;

    // Parameterized constructor to initialise details of game scene
    protected GameScene(SceneManager sceneManager) {
        this.pauseSceneState = false;
        this.timeSinceLastSpawn = 0f;
        this.sceneManager = sceneManager;
        this.levelManager = new LevelManager();
        this.aiControlManager = new AIControlManager();
        this.collisionManager = new CollisionManager();
        this.entityManager = new EntityManager();
        this.effectManager = new EffectManager();
    }

    // Check if level assets available for selected level, if not game ends
    @Override
    public void show() {
        sceneLevelAssets = levelManager.retrieveLevelAssets();

        if (sceneLevelAssets == null) {
            levelManager.setLevelNumber(1);
            sceneManager.transitionScenes("end");
        } else {
            try {
                setSceneBackgroundTexture(new Texture(Gdx.files.internal(sceneLevelAssets.getLevelBackground())));
                getSoundManager().loadSoundEffect(new String[]{"COLLECTCHERRY", "COLLECTPOINTS", "GAINHEALTH", "EXPLOSION", "TELEPORT", "PLAYERHIT", "PLAYERDEATH"});
                getSoundManager().loadBackgroundMusic(sceneLevelAssets);
                getSoundManager().playBackgroundMusic(sceneLevelAssets.getLevelTitle(), true);
                uiManager = new UiManager(sceneManager.getBatch(), getCamera().getUiViewport());
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
        Player player = (Player) entityManager.getPlayersList().get(0);
        SpriteBatch batch = sceneManager.getBatch();

        // Set up listeners on the player
        player.setHealthChangeListener(newHealth -> uiManager.getUiGameHUD().updateHealth(newHealth));
        player.setScoreChangeListener(newScore -> uiManager.getUiGameHUD().updateScore(newScore));

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Focus the camera on our player
        getCamera().focusCamera(player.getPosX(), player.getPosY(), batch);

        // Draw our game scene
        drawScene(batch, getSceneBackgroundTexture(), entityManager, effectManager);

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseSceneState = !pauseSceneState;
        }

        // If not paused, initialise all forms of behavior and movement and timers
        if (!pauseSceneState) {
            entityManager.initialiseEntityActions(preferredControls, effectManager, getSoundManager());
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap().get("ai"), player);
            collisionManager.initializeCollisions(entityManager.getEntityMap(), getSoundManager());
            entityManager.respawnEntities(sceneLevelAssets);
            effectManager.updateEffects();
        }

        // Updating of player entity status
        entityManager.checkPlayerEntityStatus(sceneLevelAssets, getSoundManager());

        // Advance to next level or end game
        if (levelManager.levelCleared(entityManager.getPlayersList())) {
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle());
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            sceneManager.transitionScenes("game");
        }

        // End game if player loses
        if (player.getLoseStatus()) {
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle()); // for now
            levelManager.setLevelNumber(1);
            sceneManager.transitionScenes("end");
        }

        // Render HUD
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }

    // Upon switching screens dispose all stuff on the screen
    public void dispose() {
        entityManager.disposeEntities();
        effectManager.disposeEffects();
        getSoundManager().disposeSounds();
    };

    // Get time since last spawn
    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    // Set time since last spawn
    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
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
