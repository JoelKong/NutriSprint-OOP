package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLayer.UI.UiManager;

// Start Scene class inherited from Scenes; every scene needs a new stage hence a new uiManager, we keep a reference to scenemanager for ease of access
public class StartScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor getting scene manager reference and passing it to our parent scenes
    protected StartScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    // Load resources
    @Override
    public void show() {
        this.uiManager = new UiManager(getSceneManager().getBatch(), getCamera().getUiViewport());
        uiManager.createStartSceneUI(getSceneManager(), getSoundManager());
        getSoundManager().loadSoundEffect(new String[]{"BUTTONCLICK"});
        getSoundManager().loadBackgroundMusic("MENU");
        getSoundManager().playBackgroundMusic("MENU", true);
    }

    // Render start scene
    @Override
    public void render(float delta) {
        // Update camera
        getCamera().updateCamera(getSceneManager().getBatch());

        // Background
        drawScene(getSceneManager().getBatch(), getSceneBackgroundTexture());

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }
}