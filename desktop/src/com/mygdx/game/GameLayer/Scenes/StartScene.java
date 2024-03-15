package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor setting start scene details
    protected StartScene(SceneManager sceneManager) {
        super(sceneManager, 1, "start");
    }

    // Load resources
    @Override
    public void show() {
        uiManager = new UiManager(batch, getCamera().getUiViewport());
        uiManager.createStartSceneUI(sceneManager, gameController);
    }

    // Render start scene
    @Override
    public void render(float delta) {
        Inputs preferredControls = getInputOutputManager().getPreferredControls();

        // Update camera
        camera.updateCamera(batch);
        // Background
        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();

        // Upon starting, change scene to game
        if (preferredControls.getStartKey()) {
            gameController.setScreen(sceneManager.getSceneMap().get("game"));
        }
    }

    // Get UI manager
    public UiManager uiManager() {
        return uiManager;
    }
}