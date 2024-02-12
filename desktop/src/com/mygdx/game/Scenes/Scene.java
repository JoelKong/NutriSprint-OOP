package com.mygdx.game.Scenes;

import com.badlogic.gdx.Screen;

public abstract class Scene implements Screen {
    private int sceneId;
    private boolean isLoaded;
    private String sceneName;

    public Scene(int sceneId, boolean isLoaded, String sceneName) {
        this.sceneId = sceneId;
        this.isLoaded = isLoaded;
        this.sceneName = sceneName;
    }

    // Override purposes
    public void render() {};

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}
