package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;
import com.mygdx.game.Main;

// End scene class inherited from scenes
public class EndScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor specifying details of end scene
    protected EndScene(Main gameController) {
        super(3, "end", gameController);
    }

    // First run of end scene
    @Override
    public void show() {
        this.uiManager = new UiManager(getGameController().getBatch(), getCamera().getUiViewport());
        uiManager.createEndSceneUI(getGameController());
        getSoundManager().loadSoundEffect(new String[]{"BUTTONCLICK"});
        getSoundManager().loadBackgroundMusic("GAMEOVER");
        getSoundManager().playBackgroundMusic("GAMEOVER", true);
        setSceneBackgroundTexture(new Texture(Gdx.files.internal("Scenes/endscenee.png")));
    }

    // Render end scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        SceneManager sceneManager = getGameController().getSceneManager();
        SpriteBatch batch = getGameController().getBatch();

        // Update camera
        getCamera().updateCamera(batch);

        // Restart back to game scene
        if (preferredControls.getRestartKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            getGameController().setScreen(sceneManager.getSceneMap().get("game"));

        // Go back to menu
        } else if (preferredControls.getMenuKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            getGameController().setScreen(sceneManager.getSceneMap().get("start"));
        }

        // Background
        // Draw our game scene
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