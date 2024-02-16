package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Scenes.LevelManager;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

// GameMaster Class
public class Main extends ApplicationAdapter {
    // Declaring of attributes (managers)
    private SimulationManager simulationManager;

    @Override
    public void create() {
        // Loading of simulation manager
        simulationManager = new SimulationManager();
    }

    @Override
    public void render() {
        // Call Simulation manager to start simulation and initialise scenes
        simulationManager.startSimulation();
    }

    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation();
    }
}
