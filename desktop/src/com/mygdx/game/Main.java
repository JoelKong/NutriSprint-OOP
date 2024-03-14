package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.GameLayer.Simulation.SimulationManager;

// GameEngine Class
public class Main extends Game {
    // Declaring of attributes (managers and batch)
    private SimulationManager simulationManager;
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


    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
