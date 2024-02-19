package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    // Parameterized Constructor setting start scene details
    public StartScene() {
        super(1, "start");
    }

    // Render Start Scene
    @Override
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, Inputs preferredControls, LevelManager levelManager) {
        // Upon starting, reset all entities raw data and change scene
        if (preferredControls.getStartKey()) {
            entityManager.initializeEntities(levelManager.retrieveLevelAssets());
            sceneManager.setCurrentScene("game");
        }

        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        batch.begin();
            renderTextAtScenePosition(batch, "Press 'Enter' to start.", "center");
        batch.end();
    }
}