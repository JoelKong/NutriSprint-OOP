package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Main;
import com.mygdx.game.UI.UiManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor setting start scene details
    protected StartScene(Main gameController) {
        super(1, "start", gameController);
    }

    @Override
    public void show() {
        this.uiManager = new UiManager(getGameController().getBatch());
        uiManager.createStartSceneUI();
    }

    // Render start scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        SceneManager sceneManager = getGameController().getSceneManager();
        SpriteBatch batch = getGameController().getBatch();

        // Update camera
        getCamera().updateCamera(batch);

        // Upon starting, change scene to game
        if (preferredControls.getStartKey()) {
            getGameController().setScreen(sceneManager.getSceneMap().get("game"));
        }

        // Background
        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();

        // Text
//        batch.begin();
//        // renderTextAtScenePosition(batch, "Press 'Enter' to start.", "center");
//        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        uiManager.getUiViewport().update(width, height, true);
        uiManager.getUiCamera().update();
    }
}