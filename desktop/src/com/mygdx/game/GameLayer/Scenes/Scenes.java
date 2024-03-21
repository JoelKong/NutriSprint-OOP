package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Camera.CameraManager;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.Entity.EntityManager;
import com.mygdx.game.GameLayer.InputOutput.InputOutputManager;
import com.mygdx.game.GameLayer.Levels.LevelManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    private Texture sceneBackgroundTexture;
    private CameraManager camera;
    private SoundManager soundManager;
    private SceneManager sceneManager; // ease of access reference to scenemanager shared across all scenes
    private static InputOutputManager inputOutputManager = new InputOutputManager(); // This manager will be shared across all scenes since its static (in the future if start or end scenes need inputs)

    // Every scene should have a new camera and sound system independent for their scenes
    protected Scenes(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.soundManager = new SoundManager();
        this.camera = new CameraManager();
        this.sceneBackgroundTexture = new Texture(Gdx.files.internal("Scenes/nutrisprint-startscene.png"));
    }

    // Draw Background on scene
    protected void drawScene(SpriteBatch batch, Texture sceneBackgroundTexture) {
        batch.begin();
            batch.draw(sceneBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    // Draw Background and Entities on scene and other relevant effects
    protected void drawScene(SpriteBatch batch, Texture sceneBackgroundTexture, EntityManager entityManager, EffectManager effectManager) {
        batch.begin();
            batch.draw(sceneBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            entityManager.drawEntities(batch);
            effectManager.drawEffects(batch);
        batch.end();
    }

    // Methods to override from the Screen LibGdx class
    public void show() {};

    public void render(float delta) {};

    // Maintain camera on resizing
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

    // Get Camera
    public CameraManager getCamera() {
        return camera;
    }

    // Get Sound Manager
    public SoundManager getSoundManager() {
        return soundManager;
    }

    // Get shared scene manager
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    // Get shared input output manager
    public static InputOutputManager getInputOutputManager() {
        return inputOutputManager;
    }
}
