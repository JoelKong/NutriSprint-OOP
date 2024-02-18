package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes implements Screen {
    private SpriteBatch batch;

    public StartScene() {
        super(1, "start");
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {
        // Call to super if needed, and any additional show logic
    }

    @Override
    public void render(SceneManager sceneManager, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {
        Inputs preferredControls = inputOutputManager.getPreferredControls();


        if (preferredControls.getStartKey()) {
            entityManager.initializeEntities(levelManager.retrieveCurrentLevelAssets());
            sceneManager.setCurrentScene("game");
        }

        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen
        renderTextCentered(batch, "Press 'Enter' to start.");
        // Call render from Scene if needed and any additional render logic
    }

    // Implement or override other methods from Scene if needed


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
        // Any dispose logic
    }
}