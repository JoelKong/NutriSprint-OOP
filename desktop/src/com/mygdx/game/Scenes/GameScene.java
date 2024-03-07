package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.PlayerControls;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Levels.Levels;
import com.mygdx.game.Main;
import com.mygdx.game.UI.UiManager;

// GameScene class inherited from scenes
public class GameScene extends Scenes {
    // Declare attributes
    private Levels sceneLevelAssets;
    private UiManager uiManager;
    private LevelManager levelManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;

    // Parameterized constructor to initialise details of game scene
    protected GameScene(Main gameController) {
        super(2, "game", gameController);
        this.levelManager = new LevelManager(); // singleton
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AIControlManager();
        this.collisionManager = new CollisionManager();
    }

    @Override
    public void show() {
        this.uiManager = new UiManager(getGameController().getBatch());

        uiManager.startGameHUD();

        sceneLevelAssets = levelManager.retrieveLevelAssets();

        if (sceneLevelAssets == null) {
            levelManager.setLevelNumber(1);
            getGameController().setScreen(getGameController().getSceneManager().getSceneMap().get("end"));
        } else {

            try {
                getGameController().getEntityManager().initializeEntities(sceneLevelAssets);
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
        SceneManager sceneManager = getGameController().getSceneManager();
        EntityManager entityManager = getGameController().getEntityManager();
        SpriteBatch batch = getGameController().getBatch();

        // Clear the screen
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Update camera
        getCamera().focusCamera(entityManager.getPlayersList().get(0).getPosX(), entityManager.getPlayersList().get(0).getPosY(), batch);

        // Draw entities and render text
        batch.begin();
            entityManager.drawEntities(batch);
            // renderTextAtScenePosition(batch, sceneLevelAssets.getLevelTitle(), "topleft");
            // renderTextAtScenePosition(batch, "Press P to pause", "top");
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
            levelManager.setLevelNumber(levelManager.getLevelNumber() + 1);
            getGameController().setScreen(sceneManager.getSceneMap().get("game"));
        }

        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();

        //        if (contition) {
//            // Tells EntityManager to make give me a list of entities
//            List<GameEntity> newlistOFEntities = entityManager.getListOfEntities("AI",5);
//            // code to render out the new entities
//        }
    }

    // Get Level Manager
    public LevelManager getLevelManager() {
        return levelManager;
    }

    // Get Player Controls Managers
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
}
