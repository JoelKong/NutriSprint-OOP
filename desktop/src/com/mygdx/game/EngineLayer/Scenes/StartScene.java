package com.mygdx.game.EngineLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.Main;
import com.mygdx.game.EngineLayer.UI.UiManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor setting start scene details
    protected StartScene(Main gameController) {
        super(1, "start", gameController);
    }

    // Load resources
    @Override
    public void show() {
        uiManager = new UiManager(getGameController().getBatch(), getCamera().getUiViewport());
        uiManager.createStartSceneUI(getGameController());
        setSceneBackgroundTexture(new Texture(Gdx.files.internal("Scenes/nutrisprint-startscene.png")));
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
        drawScene(batch, getSceneBackgroundTexture());

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }

    // Get UI manager
    public UiManager uiManager() {
        return uiManager;
    }
}