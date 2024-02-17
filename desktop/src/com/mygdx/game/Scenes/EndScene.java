package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;

public class EndScene extends Scenes implements Screen {
    private Stage stage;
    private SpriteBatch batch;

    public EndScene() {
        super(3, "end");
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(SceneManager sceneManager, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {
        Inputs preferredControls = inputOutputManager.getPreferredControls();

        if (preferredControls.getRestartKey()) {
            levelManager.setLevelNumber(1);
            entityManager.initializeEntities(levelManager.retrieveCurrentLevelAssets());
            sceneManager.setCurrentScene("game");

        } else if (preferredControls.getMenuKey()) {
            levelManager.setLevelNumber(1);
            sceneManager.setCurrentScene("start");
        }

        Gdx.gl.glClearColor(1, 0, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        renderTextCentered(batch, "Game ended, press 'R' to restart or 'M' to return to Main Menu");
    }

    // Implement or override other methods from Scene if needed

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        // Any resize logic
    }

    @Override
    public void pause() {
        // Any pause logic
    }

    @Override
    public void resume() {
        // Any resume logic
    }

    @Override
    public void hide() {
        // Any hide logic
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
    }
}