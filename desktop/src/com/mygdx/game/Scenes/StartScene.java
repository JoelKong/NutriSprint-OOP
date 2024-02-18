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
    // Declare Attributes
    private SpriteBatch batch;

    // Parameterized Constructor setting start scene details
    public StartScene() {
        super(1, "start");
        this.batch = new SpriteBatch();
    }

    // Render Start Scene
    @Override
    public void render(SceneManager sceneManager, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {
        Inputs preferredControls = inputOutputManager.getPreferredControls(); // Get preferred controls

        // Upon starting, reset all entities raw data and change scene
        if (preferredControls.getStartKey()) {
            entityManager.initializeEntities(levelManager.retrieveCurrentLevelAssets());
            sceneManager.setCurrentScene("game");
        }

        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen
        renderTextAtPosition(batch, "Press 'Enter' to start.", "center");
    }
}