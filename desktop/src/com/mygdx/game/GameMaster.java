package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.Simulation.SimulationManager;

// GameMaster Class
public class GameMaster extends ApplicationAdapter {
    // Declaring of attributes
    private SimulationManager simulationManager;

    @Override
    public void create() {
        // Loading of simulation manager
        simulationManager = new SimulationManager();
    }

    @Override
    public void render() {
        // Start Simulation Loop
        simulationManager.startSimulation();
    }

    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation();
    }
}
