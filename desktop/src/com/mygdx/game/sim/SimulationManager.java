package com.mygdx.game.sim;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entity.AIControlManager;
import com.mygdx.game.collision.CollisionManager;
import com.mygdx.game.entity.PlayerControlManager;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.entity.PlayerControls;
import com.mygdx.game.io.InputOutputManager;
import com.mygdx.game.io.Inputs;
import com.mygdx.game.level.LevelManager;
import com.mygdx.game.scene.*;

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
