package com.mygdx.game.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AI.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Scenes.*;

public class SimulationManager {
    // Declare Variables
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private PlayerControlManager controlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private SpriteBatch batch;

    // Initialise all managers when simulation is created
    public SimulationManager() {
        batch = new SpriteBatch();
        entityManager = new EntityManager();
        sceneManager = new SceneManager();
        inputOutputManager = new InputOutputManager();
        controlManager = new PlayerControlManager();
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
    }

    // Start the simulation and listen to requests that needs to be looped
    public void startSimulation() throws CloneNotSupportedException {
        Inputs preferredControls = inputOutputManager.getPreferredControls();
        Scenes currentScene = sceneManager.getCurrentScene();
        sceneManager.loadScene(currentScene);

        if (currentScene instanceof StartScene) { // Start Scene
            if (Gdx.input.isKeyPressed(preferredControls.getStartKey())) {
                entityManager.initializeEntities();
                sceneManager.setCurrentScene(sceneManager.getSceneMap().get("game"));
            }

        } else if (currentScene instanceof GameScene) { // Game Scene
            entityManager.initializePlayerMovement(preferredControls, controlManager.getPlayerControls());
            aiControlManager.initializeAIBehavior(entityManager.getEntityMap());
            batch.begin();
            entityManager.drawEntities(batch);
            batch.end();
            // for now for testing
            if ((Gdx.input.isKeyPressed(preferredControls.getUpKey()))) {
                sceneManager.setCurrentScene(sceneManager.getSceneMap().get("end"));
            }
            // check collision function from collision class
            // sceneManager.setCurrentScene(sceneManager.getSceneMap().get("end"));

        } else if (currentScene instanceof EndScene) { // End Scene
            if (Gdx.input.isKeyPressed(preferredControls.getRestartKey())) {
                entityManager.initializeEntities();
                sceneManager.setCurrentScene(sceneManager.getSceneMap().get("game"));
            } else if (Gdx.input.isKeyPressed(preferredControls.getMenuKey())) {
                sceneManager.setCurrentScene(sceneManager.getSceneMap().get("start"));
            }
        }
    }

    // Ends the simulation and disposes everything used
    public void endSimulation() {
        //dispose
    }
}
