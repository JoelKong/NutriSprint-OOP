package com.mygdx.game.Simulation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Scenes.*;

// Simulation manager class
public class SimulationManager {
    // Default Constructor
    public SimulationManager() {}

    // Start the simulation and initialize the first scene
    public void startSimulation(SceneManager sceneManager, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {

        sceneManager.initializeScenes(entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
    }

    public void startDrawingSceneObjects(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, LevelManager levelManager) {
        sceneManager.drawSceneObjects(batch, entityManager, levelManager);
    }

    // Ends the simulation and disposes everything used
    public void endSimulation(EntityManager entityManager, SceneManager sceneManager) {
        entityManager.disposeEntities();
        for (Scenes scenes: sceneManager.getSceneMap().values()) {
            scenes.dispose();
        }
    }
}
