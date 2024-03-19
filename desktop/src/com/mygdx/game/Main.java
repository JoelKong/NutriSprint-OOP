package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.mygdx.game.GameLayer.Scenes.SceneManager;

// Singleton SceneManager starting off with initialising our start scene, the GameController is being passed to it
public class Main extends Game {
    private SceneManager sceneManager;

    @Override
    public void create() {
        this.sceneManager = new SceneManager(this);
        sceneManager.initializeScenes();
    }

    @Override
    public void render() {
        // Render starting from start screen
        super.render();
    }
}