package com.mygdx.game.Simulation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.PlayerControls;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Scenes.*;

// Simulation manager class
public class SimulationManager {
    // Default Constructor
    public SimulationManager() {}

    // Start the simulation and initialize the first scene
    public void startSimulation(SpriteBatch batch, SceneManager sceneManager, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {

        Inputs preferredControls = inputOutputManager.getPreferredControls();
        PlayerControls playerControls = playerControlManager.getPlayerControls();

        sceneManager.initializeScenes(batch, entityManager, collisionManager, aiControlManager, preferredControls, playerControls, levelManager);
    }

    // Ends the simulation and disposes everything used
    public void endSimulation(SpriteBatch batch, EntityManager entityManager, SceneManager sceneManager) {
        entityManager.disposeEntities();
        sceneManager.disposeScenes(batch);
    }
}
