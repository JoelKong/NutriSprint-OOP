package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;

// End scene class inherited from scenes
public class EndScene extends Scenes {
    private UiManager uiManager;
    private SceneManager sceneManager;

    // Parameterized Constructor specifying details of end scene
    protected EndScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    // First run of end scene
    @Override
    public void show() {
        this.uiManager = new UiManager(sceneManager.getBatch(), getCamera().getUiViewport());
        uiManager.createEndSceneUI(sceneManager);
        getSoundManager().loadSoundEffect(new String[]{"BUTTONCLICK"});
        getSoundManager().loadBackgroundMusic("GAMEOVER");
        getSoundManager().playBackgroundMusic("GAMEOVER", true);
        setSceneBackgroundTexture(new Texture(Gdx.files.internal("Scenes/endscene.png")));
    }

    // Render end scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getInputOutputManager().getPreferredControls();

        // Update camera
        getCamera().updateCamera(sceneManager.getBatch());

        // Restart back to game scene (will change on button click)
        if (preferredControls.getRestartKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            sceneManager.transitionScenes("game");

        // Go back to menu (will change on button click)
        } else if (preferredControls.getMenuKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            sceneManager.transitionScenes("start");
        }

        // Draw our game scene
        drawScene(sceneManager.getBatch(), getSceneBackgroundTexture());

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }

    // Get UI manager
    public UiManager uiManager() {
        return uiManager;
    }
}