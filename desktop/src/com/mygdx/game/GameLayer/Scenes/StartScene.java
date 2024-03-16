package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    private UiManager uiManager;
    private SceneManager sceneManager;

    // Parameterized Constructor setting start scene details
    protected StartScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    // Load resources
    @Override
    public void show() {
        uiManager = new UiManager(sceneManager.getBatch(), getCamera().getUiViewport());
        uiManager.createStartSceneUI(sceneManager);
        getSoundManager().loadSoundEffect(new String[]{"BUTTONCLICK"});
        getSoundManager().loadBackgroundMusic("MENU");
        getSoundManager().playBackgroundMusic("MENU", true);
    }

    // Render start scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getInputOutputManager().getPreferredControls();

        // Update camera
        getCamera().updateCamera(sceneManager.getBatch());

        // Upon starting, change scene to game (will change this when ui clicks)
        if (preferredControls.getStartKey()) {
            getSoundManager().stopBackgroundMusic("MENU");
            sceneManager.transitionScenes("game");
        }

        // Background
        drawScene(sceneManager.getBatch(), getSceneBackgroundTexture());

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }
}