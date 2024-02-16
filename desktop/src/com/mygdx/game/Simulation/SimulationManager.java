package com.mygdx.game.Simulation;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Scenes.*;

// Simulation manager class
public class SimulationManager {
    // Default Constructor
    public SimulationManager() {}

    // Start the simulation and listen to requests that needs to be looped
    public void startSimulation(SceneManager sceneManager, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {

        sceneManager.initializeScenes(entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
    }

    // Ends the simulation and disposes everything used
    public void endSimulation(EntityManager entityManager, SceneManager sceneManager) {
        entityManager.disposeEntities();
        for (Scenes scenes: sceneManager.getSceneMap().values()) {
            scenes.dispose();
        }
    }
}
