package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;

// End scene class inherited from scenes
public class EndScene extends Scenes {
    // Parameterized Constructor specifying details of end scene
    public EndScene() {
        super(3, "end");
    }

    // Render end scene
    @Override
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {
        Inputs preferredControls = inputOutputManager.getPreferredControls(); // Get preferred controls

        // Upon restarting reset level to 1 and reinitialise entity raw data
        if (preferredControls.getRestartKey()) {
            levelManager.setLevelNumber(1);
            entityManager.initializeEntities(levelManager.retrieveLevelAssets(levelManager.getLevelNumber()));
            sceneManager.setCurrentScene("game");

        // Upon going back menu, reset level to 1
        } else if (preferredControls.getMenuKey()) {
            levelManager.setLevelNumber(1);
            sceneManager.setCurrentScene("start");
        }

        Gdx.gl.glClearColor(1, 0, 0, 1); // setting clear color to red
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        batch.begin();
            renderTextAtScenePosition(batch, "Game ended, press 'R' to restart or 'M' to return to Main Menu", "center");
        batch.end();
    }
}