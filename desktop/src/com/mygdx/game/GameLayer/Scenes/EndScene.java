package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameLayer.InputOutput.Inputs;

// End scene class inherited from scenes
public class EndScene extends Scenes {

    // Parameterized Constructor getting scene manager reference and passing it to our parent scenes
    protected EndScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    // First run of end scene
    @Override
    public void show() {
        getUiManager().createEndSceneUI(getSceneManager());
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
        getCamera().updateCamera(getSceneManager().getBatch());

        // Restart back to game scene (will change on button click)
        if (preferredControls.getRestartKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            getSceneManager().transitionScenes("game");

        // Go back to menu (will change on button click)
        } else if (preferredControls.getMenuKey()) {
            getSoundManager().stopBackgroundMusic("GAMEOVER");
            getSceneManager().transitionScenes("start");
        }

        // Draw our game scene
        drawScene(getSceneManager().getBatch(), getSceneBackgroundTexture());

        // UI
        getUiManager().getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        getUiManager().getUiStage().draw();
    }
}