package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.Simulation.SimulationManager;


public class GameMaster extends ApplicationAdapter {
    // Declaring of variables
    private SimulationManager simulationManager;

    @Override
    public void create() {
        // Loading of simulation manager
        simulationManager = new SimulationManager();
    }

    @Override
    public void render() {
        // Start Simulation
        simulationManager.startSimulation();
    }

    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation();
    }
}
