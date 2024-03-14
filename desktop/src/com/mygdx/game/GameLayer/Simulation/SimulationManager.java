package com.mygdx.game.GameLayer.Simulation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.Main;

// Simulation manager class
public class SimulationManager {
    private Main gameController;
    private SceneManager sceneManager;
    // Default Constructor
    public SimulationManager() {}

    public SimulationManager(Main gameController) {
        this.gameController = gameController;
        this.sceneManager = new SceneManager(gameController);
    }

    // Start the simulation and initialize the first scene
    public void startSimulation() {
        sceneManager.initializeScenes();
    }
    public void startSimulation(SceneManager sceneManager) {
        sceneManager.initializeScenes();
    }

    // Ends the simulation and disposes everything used
    public void endSimulation(SpriteBatch batch) {
        batch.dispose();
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
