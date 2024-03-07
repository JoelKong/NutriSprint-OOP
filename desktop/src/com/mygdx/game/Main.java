package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

// GameEngine Class
public class Main extends Game {
    // Declaring of attributes (managers)
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private SpriteBatch batch;
    private Game game;

    @Override
    public void create() {
        this.game = this;
        this.simulationManager = new SimulationManager();
        this.entityManager = new EntityManager();
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
        simulationManager.endSimulation(batch, entityManager, sceneManager);
    }

    public SimulationManager getSimulationManager() {
        return simulationManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Game getGame() {
        return game;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
