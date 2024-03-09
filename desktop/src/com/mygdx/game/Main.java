package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.Scenes.SceneManager;
import com.mygdx.game.EngineLayer.Simulation.SimulationManager;

// Declaring of singletons
public class Main extends Game {
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
        super.render();
    }

    @Override
    public void dispose() {
        simulationManager.endSimulation(batch);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
