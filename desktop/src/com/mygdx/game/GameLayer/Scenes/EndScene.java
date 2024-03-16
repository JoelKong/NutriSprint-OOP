package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;

// End scene class inherited from scenes
public class EndScene extends Scenes {
    private UiManager uiManager;

    // Parameterized Constructor getting scene manager reference and passing it to our parent scenes
    protected EndScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    // Load Resources
    @Override
    public void show() {
        this.uiManager = new UiManager(getSceneManager().getBatch(), getCamera().getUiViewport());
        uiManager.createEndSceneUI(getSceneManager(), getSoundManager());
        getSoundManager().loadSoundEffect(new String[]{"BUTTONCLICK"});
        getSoundManager().loadBackgroundMusic("GAMEOVER");
        getSoundManager().playBackgroundMusic("GAMEOVER", true);
        setSceneBackgroundTexture(new Texture(Gdx.files.internal("Scenes/endscene.png")));
    }

    // Render end scene
    @Override
    public void render(float delta) {
        // Update camera
        getCamera().updateCamera(getSceneManager().getBatch());

        // Draw our game scene
        drawScene(getSceneManager().getBatch(), getSceneBackgroundTexture());

        // UI
        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        uiManager.getUiStage().draw();
    }
}