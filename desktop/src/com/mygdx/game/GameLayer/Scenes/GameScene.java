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

// GameScene class inherited from scenes, only contain managers which belong in gamescene
public class GameScene extends Scenes {
    private Levels sceneLevelAssets;
    private boolean pauseSceneState;
    private LevelManager levelManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private EntityManager entityManager;
    private EffectManager effectManager;
    private UiManager uiManager;

    // Parameterized Constructor getting scene manager reference and passing it to our parent scenes
    protected GameScene(SceneManager sceneManager) {
        super(sceneManager);
        this.pauseSceneState = false;
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
            getSceneManager().transitionScenes("end");
        } else {
            try {
                setSceneBackgroundTexture(new Texture(Gdx.files.internal(sceneLevelAssets.getLevelBackground())));
                getSoundManager().loadSoundEffect(new String[]{"COLLECTCHERRY", "COLLECTPOINTS", "GAINHEALTH", "EXPLOSION", "TELEPORT", "PLAYERHIT", "PLAYERDEATH"});
                getSoundManager().loadBackgroundMusic(sceneLevelAssets);
                getSoundManager().playBackgroundMusic(sceneLevelAssets.getLevelTitle(), true);
                this.uiManager = new UiManager(getSceneManager().getBatch(), getCamera().getUiViewport());
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
        // Get necessary data for ease of access
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        Player player = (Player) entityManager.getPlayersList().get(0);
        SpriteBatch batch = getSceneManager().getBatch();

        // Set up listeners on the player
        player.setHealthChangeListener(newHealth -> uiManager.getUiGameHUD().updateHudHealth(newHealth));
        player.setScoreChangeListener(newScore -> uiManager.getUiGameHUD().updateHudScore(newScore));

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Focus the camera on our player
        getCamera().focusCamera(player.getPosX(), player.getPosY(), batch);

        // Draw our game scene along with its entities
        drawScene(batch, getSceneBackgroundTexture(), entityManager, effectManager);

        // Pause and Resume Game
        if (preferredControls.getPauseKey()) {
            pauseSceneState = !pauseSceneState;
        }

        // If not paused, initialise all forms of behavior and movement and timers
        if (!pauseSceneState) {
            entityManager.initialiseEntityActions(preferredControls, effectManager, getSoundManager());
            aiControlManager.initializeAIBehavior(entityManager.getAiEntityList(), player);
            collisionManager.initializeCollisions(entityManager.getEntityMap(), getSoundManager());
            entityManager.respawnEntities(sceneLevelAssets);
            effectManager.updateEffects();
        }

        // Updating of player entity status
        entityManager.checkPlayerEntityStatus(sceneLevelAssets, getSoundManager());

        // Advance to next level
        if (levelManager.levelCleared(entityManager.getPlayersList())) {
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle());
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            getSceneManager().transitionScenes("game");
        }

        // End game if player loses
        if (player.getLoseStatus()) {
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle());
            levelManager.setLevelNumber(1);
            getSceneManager().transitionScenes("end");
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

    // Get Pause Scene State
    public boolean isPauseSceneState() {
        return pauseSceneState;
    }

    // Set Pause Scene State
    public void setPauseSceneState(boolean pauseSceneState) {
        this.pauseSceneState = pauseSceneState;
    }
}
