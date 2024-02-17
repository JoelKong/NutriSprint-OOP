package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

// GameMaster Class
public class Main extends ApplicationAdapter {
    // Declaring of attributes (managers)
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private LevelManager levelManager;

    @Override
    public void create() {
        // Creation of managers
        simulationManager = new SimulationManager();
        sceneManager = new SceneManager();
        inputOutputManager = new InputOutputManager();
        playerControlManager = new PlayerControlManager();
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();
        levelManager = new LevelManager();
    }

    @Override
    public void render() {
        // Call Simulation manager to start simulation and initialise scenes
        simulationManager.startSimulation(sceneManager, entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
    }

    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation(entityManager, sceneManager);
    }
}
