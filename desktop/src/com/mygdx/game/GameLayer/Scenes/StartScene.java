package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLayer.InputOutput.Inputs;

// Start Scene class inherited from Scenes; every scene needs a new stage hence a new uiManager, we keep a reference to scenemanager for ease of access
public class StartScene extends Scenes {

    // Parameterized Constructor getting scene manager reference and passing it to our parent scenes
    protected StartScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    // Load resources
    @Override
    public void show() {
        getUiManager().createStartSceneUI(getSceneManager());
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
        getCamera().updateCamera(getSceneManager().getBatch());

        // Upon starting, change scene to game (will change this when ui clicks)
        if (preferredControls.getStartKey()) {
            getSoundManager().stopBackgroundMusic("MENU");
            getSceneManager().transitionScenes("game");
        }

        // Background
        drawScene(getSceneManager().getBatch(), getSceneBackgroundTexture());

        // UI
        getUiManager().getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        getUiManager().getUiStage().draw();
    }
}