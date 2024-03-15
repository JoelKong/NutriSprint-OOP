package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Camera.CameraManager;
import com.mygdx.game.GameLayer.Entity.EntityManager;
import com.mygdx.game.GameLayer.InputOutput.InputOutputManager;

// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private Texture sceneBackgroundTexture;

    protected Game gameController;
    protected SceneManager sceneManager;
    protected SpriteBatch batch;
    protected InputOutputManager inputOutputManager;
    protected CameraManager camera;
    // Parameterized constructor to specify details of scenes
    protected Scenes(SceneManager sceneManager, int sceneId, String sceneName) {
        this.sceneManager = sceneManager;
        this.gameController = sceneManager.getGameController();
        this.batch = sceneManager.getBatch();

        this.inputOutputManager = new InputOutputManager();
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

    // Draw Background and Entities on scene
    public void drawScene(SpriteBatch batch, Texture sceneBackgroundTexture, EntityManager entityManager) {
        batch.begin();
            batch.draw(sceneBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            entityManager.drawEntities(batch);
        batch.end();
    }

    // Methods to override from the Screen LibGdx class
    public void show() {}

    abstract public void render(float delta);

    public void resize(int width, int height) {
        camera.resetCameraOnResize(width, height);
        camera.getUiViewport().update(width, height, true);
        camera.getUiCamera().update();
    };

    public void pause() {};

    public void resume() {};

    public void hide() {};

    public void dispose() {};

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
//    public Game getGameController() {
//        return gameController;
//    }

    // Get Camera
    public CameraManager getCamera() {
        return camera;
    }

    // Get Input Output Manager
    public InputOutputManager getInputOutputManager() {
        return inputOutputManager;
    }
}
