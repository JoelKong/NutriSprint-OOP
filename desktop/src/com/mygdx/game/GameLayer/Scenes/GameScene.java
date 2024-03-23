package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLayer.Collisions.CollisionManager;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.Entity.*;
import com.mygdx.game.GameLayer.InputOutput.InputOutputManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.LevelManager;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.UI.UiManager;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

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
    private String sceneDialogues[];

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


    @Override
    public void show() {
        sceneLevelAssets = levelManager.retrieveLevelAssets();
        // Check if level assets available for selected level, if not game ends
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

                uiManager.startGameHUD(sceneLevelAssets.getDialogue());
                uiManager.updateGameHUDLevel(sceneLevelAssets.getLevelTitle());
                uiManager.updateGameHudObjective(sceneLevelAssets.getScoreNeeded());
                entityManager.initializeEntities(sceneLevelAssets);
                this.sceneDialogues = sceneLevelAssets.getDialogue();
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
        Isaac player = (Isaac) entityManager.getPlayerEntityList().get(0);
        SpriteBatch batch = getSceneManager().getBatch();

        // Assuming sceneDialogues is not empty and has elements to remove
        if (sceneDialogues.length > 0) {
            this.pauseSceneState = false;
            uiManager.updateGameHudDialogue(sceneDialogues[0]); // Update HUD with the first dialogue
            if (preferredControls.getProgressDialogueKey()) {
                // Shift the rest of the elements to the left by one position and create a new array
                sceneDialogues = Arrays.copyOfRange(sceneDialogues, 1, sceneDialogues.length);
            }
        } else {
            uiManager.updateGameHudDialogueInvisible();
        }

        // Set up listeners on the player
        player.setEntityAttributeListener(new EntityAttributeListener() {
            @Override
            public void onTeleportCooldownChange(int newTeleportCooldown) {
                uiManager.getUiGameHUD().updateHudTeleportCooldown(newTeleportCooldown, player.getTeleportCooldown());
            }

            @Override
            public void onScoreChange(int newScore) {
                uiManager.getUiGameHUD().updateHudScore(newScore);
            }

            @Override
            public void onHealthChange(int newHealth) {
                uiManager.getUiGameHUD().updateHudHealth(newHealth);
            }

            public void onExplodeMeterChange(int newExplodeMeterCount) {
                uiManager.getUiGameHUD().updateHudExplodeMeterCount(newExplodeMeterCount);
            }
        });

        player.notifyTeleportCooldownChange();
        player.notifyExplodeMeterChange();
        player.notifyHealthChange();
        player.notifyScoreChange();

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
        if (!pauseSceneState && sceneDialogues.length == 0) {
            entityManager.initialiseEntityActions(preferredControls, effectManager, getSoundManager());
            aiControlManager.initializeAIBehavior(entityManager.getAiEntityList(), player);
            collisionManager.initializeCollisions(entityManager, getSoundManager());
            entityManager.respawnEntities(sceneLevelAssets);
            effectManager.updateEffects();
        }

        // Updating of player entity status
        entityManager.checkPlayerEntityStatus(sceneLevelAssets, getSoundManager());

        // Advance to next level
        if (levelManager.levelCleared(entityManager.getPlayerEntityList())) {
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle());
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            getSceneManager().transitionScenes("game");
        }

        // End game if player loses
        if (player.getLoseStatus()) {
            entityManager.removeEntity(player);
            getSoundManager().stopBackgroundMusic(sceneLevelAssets.getLevelTitle());
            levelManager.setLevelNumber(1);

            // Store game data state in bin file
            String filename = "game_data.bin"; // Choose a filename
            String levelTitle = sceneLevelAssets.getLevelTitle();
            int playerScore = player.getScore();
            try {
                // Open file in binary mode
                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename));

                // Write level title and player score
                outputStream.writeUTF(levelTitle);
                outputStream.writeInt(playerScore);

                // Close the output stream
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            getSceneManager().transitionScenes("end");
        }

        // Render HUD
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
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
