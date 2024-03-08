package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Camera.CameraManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Main;


// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private Main gameController;
    private CameraManager camera;
    private InputOutputManager inputOutputManager;

    // Parameterized constructor to specify details of scenes
    protected Scenes(int sceneId, String sceneName, Main gameController) {
        this.gameController = gameController;
        this.inputOutputManager = new InputOutputManager();
        this.camera = new CameraManager();
        this.sceneId = sceneId;
        this.sceneName = sceneName;
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

    public void dispose() {};


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
}
