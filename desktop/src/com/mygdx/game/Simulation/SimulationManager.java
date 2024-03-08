package com.mygdx.game.Simulation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scenes.*;

// Simulation manager class
public class SimulationManager {
    // Default Constructor
    public SimulationManager() {}

    // Start the simulation and initialize the first scene
    public void startSimulation(SceneManager sceneManager) {
        sceneManager.initializeScenes();
    }

    // Ends the simulation and disposes everything used
    public void endSimulation(SpriteBatch batch) {
        batch.dispose();
    }
}
