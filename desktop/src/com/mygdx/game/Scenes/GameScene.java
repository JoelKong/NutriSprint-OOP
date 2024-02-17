package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private Stage stage;
    private BitmapFont font;
    private GlyphLayout layout;
    private boolean pauseState;
    private Levels levelAssets;

    public GameScene() {
        super(2, "game");
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.layout = new GlyphLayout();
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

        // Text layout
        layout.setText(font, "Press 'P' to pause game.");
        float textWidth = layout.width;
        float textHeight = layout.height;
        float screenTextX = (Gdx.graphics.getWidth() - textWidth) / 2;
        float margin = 20;
        float screenTextY = Gdx.graphics.getHeight() - margin;

        // Draw entities and text
        batch.begin();
        entityManager.drawEntities(batch);
        font.draw(batch, layout, screenTextX, screenTextY);
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
        layout.setText(font, "Game Paused");

        float textWidth = layout.width;
        float textHeight = layout.height;

        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float margin = 20;
        float y = Gdx.graphics.getHeight() - margin;

        batch.begin();
        font.draw(batch, layout, x, y);
        batch.end();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    // Implement or override other methods as needed, following the same pattern
    // ...

    @Override
    public void dispose() {// Call the superclass dispose method if it contains important logic
        batch.dispose();
        // Dispose of any additional resources specific to GameScene
    }
}
