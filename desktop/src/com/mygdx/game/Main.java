package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.collision.CollisionManager;
import com.mygdx.game.entity.PlayerControlManager;
import com.mygdx.game.entity.AIControlManager;
import com.mygdx.game.entity.EntityManager;
import com.mygdx.game.io.InputOutputManager;
import com.mygdx.game.level.LevelManager;
import com.mygdx.game.scene.SceneManager;
import com.mygdx.game.sim.SimulationManager;

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
    private SpriteBatch batch;

    @Override
    public void create() {
        // Creation of managers and batch
        simulationManager = new SimulationManager();
        sceneManager = new SceneManager();
        inputOutputManager = new InputOutputManager();
        playerControlManager = new PlayerControlManager();
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
        entityManager = new EntityManager();
        levelManager = new LevelManager();
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        // Call Simulation manager to start simulation and initialise scenes
        simulationManager.startSimulation(batch, sceneManager, entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
    }


    @Override
    public void dispose() {
        // End Simulation
        simulationManager.endSimulation(batch, entityManager, sceneManager);
    }
}
