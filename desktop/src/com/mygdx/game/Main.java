package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

// GameEngine Class
public class Main extends Game {
    // Declaring of attributes (managers and batch)
    private SimulationManager simulationManager;
    private SceneManager sceneManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        this.simulationManager = new SimulationManager();
        this.batch = new SpriteBatch();
        this.sceneManager = new SceneManager(this);
        this.simulationManager.startSimulation(sceneManager);
    }

    @Override
    public void render() {
        // Render starting from start screen
        super.render();
    }


    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation(batch);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
