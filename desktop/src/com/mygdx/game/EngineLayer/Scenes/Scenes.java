package com.mygdx.game.EngineLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.Camera.CameraManager;
import com.mygdx.game.EngineLayer.Effects.EffectManager;
import com.mygdx.game.EngineLayer.Entity.EntityManager;
import com.mygdx.game.EngineLayer.InputOutput.InputOutputManager;
import com.mygdx.game.EngineLayer.Sound.SoundManager;
import com.mygdx.game.Main;


// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private Texture sceneBackgroundTexture;
    private Main gameController;
    private CameraManager camera;
    private InputOutputManager inputOutputManager;
    private SoundManager soundManager;

    // Parameterized constructor to specify details of scenes
    protected Scenes(int sceneId, String sceneName, Main gameController) {
        this.gameController = gameController;
        this.inputOutputManager = new InputOutputManager();
        this.soundManager = new SoundManager();
        this.camera = new CameraManager();
        this.sceneId = sceneId;
        this.sceneName = sceneName;
    }

    // Draw Background on scene
    public void drawScene(SpriteBatch batch, Texture sceneBackgroundTexture) {
        batch.begin();
            batch.draw(sceneBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    // Draw Background and Entities on scene and other relevant effects
    public void drawScene(SpriteBatch batch, Texture sceneBackgroundTexture, EntityManager entityManager, EffectManager effectManager) {
        batch.begin();
            batch.draw(sceneBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            entityManager.drawEntities(batch);
            effectManager.drawEffects(batch);
        batch.end();
    }

    // Methods to override from the Screen LibGdx class
    public void show() {}

    abstract public void render(float delta);

    public void resize(int width, int height) {
        getCamera().resetCameraOnResize(width, height);
        getCamera().getUiViewport().update(width, height, true);
        getCamera().getUiCamera().update();
    };

    public void pause() {};

    public void resume() {};

    public void hide() {};

    public void dispose() {
        soundManager.disposeSounds();
    };

    // Get Scene Background Texture
    public Texture getSceneBackgroundTexture() {
        return sceneBackgroundTexture;
    }

    // Set Scene Background Texture
    public void setSceneBackgroundTexture(Texture sceneBackgroundTexture) {
        this.sceneBackgroundTexture = sceneBackgroundTexture;
    }

    // Get Scene ID
    public int getSceneId() {
        return sceneId;
    }

    // Set Scene ID
    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    // Get Scene Name
    public String getSceneName() {
        return sceneName;
    }

    // Set Scene Name
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    // Get game controller
    public Main getGameController() {
        return gameController;
    }

    // Get Camera
    public CameraManager getCamera() {
        return camera;
    }

    // Get Input Output Manager
    public InputOutputManager getInputOutputManager() {
        return inputOutputManager;
    }

    // Get Sound Manager
    public SoundManager getSoundManager() {
        return soundManager;
    }
}
