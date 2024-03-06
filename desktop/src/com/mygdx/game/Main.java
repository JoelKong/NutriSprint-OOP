package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Camera.CameraManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

// GameEngine Class
public class Main extends Game {
    // Declaring of attributes (managers)
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        // Creation of managers and batch
        simulationManager = new SimulationManager(); // singleton
        sceneManager = new SceneManager(this);
        entityManager = new EntityManager(); // factory
        batch = new SpriteBatch();

        // Call simulation manager to ask scenemanager to set screen
        simulationManager.startSimulation(sceneManager);
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


    public SpriteBatch getBatch() {
        return batch;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
