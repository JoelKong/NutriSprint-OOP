package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;

// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;

    // Parameterized constructor to specify details of scenes
    public Scenes(int sceneId, String sceneName) {
        this.sceneId = sceneId;
        this.sceneName = sceneName;
    }

    // Abstract method that all scenes must have
    abstract public void render(float delta);

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
}
