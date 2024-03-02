package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Main;

// End scene class inherited from scenes
public class EndScene extends Scenes {
    // Parameterized Constructor specifying details of end scene
    protected EndScene(Main gameController) {
        super(3, "end", gameController);
    }

    // Render end scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getGameController().getInputOutputManager().getPreferredControls();
        SceneManager sceneManager = getGameController().getSceneManager();
        SpriteBatch batch = getGameController().getBatch();

        // Update camera
        getCamera().updateCamera(batch);

        // Restart back to game scene
        if (preferredControls.getRestartKey()) {
            getGameController().setScreen(sceneManager.getSceneMap().get("game"));

        // Go back to menu
        } else if (preferredControls.getMenuKey()) {
            getGameController().setScreen(sceneManager.getSceneMap().get("start"));
        }

        // Background
        Gdx.gl.glClearColor(1, 0, 0, 1); // setting clear color to red
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        // Text
        batch.begin();
        renderTextAtScenePosition(batch, "Game ended, press 'R' to restart or 'M' to return to Main Menu", "center");
        batch.end();
    }
}