package com.mygdx.game.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity.AI;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.GameEntity;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Scenes.*;

import java.util.List;

public class SimulationManager {
    // Declare Variables
    private boolean pauseState;
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
        this.pauseState = false;
    }

    public boolean victoryCondition() {
        for (GameEntity aiEntity: entityManager.getEntityMap().get("spawnables")) {
            AI ai = (AI) aiEntity;
            if (!ai.getPopFromScreen()) {
                return false;
            }
        }
        return true;
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
            if (!pauseState) {
                entityManager.initializePlayerMovement(preferredControls, controlManager.getPlayerControls());
                aiControlManager.initializeAIBehavior(entityManager.getEntityMap());
            }
            collisionManager.initializeCollisions(entityManager.getEntityMap());
            batch.begin();
            entityManager.drawEntities(batch);
            batch.end();

            // Pause and Resume Game
            if ((Gdx.input.isKeyJustPressed(preferredControls.getPauseKey()))) {
                pauseState = !pauseState;
            }

            // Check if all drops are gone
            if (victoryCondition()) {
                sceneManager.setCurrentScene(sceneManager.getSceneMap().get("end"));
            }

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

    // Get Pause State
    public boolean getPauseState() {
        return pauseState;
    }

    // Set Pause State
    public void setPauseState(boolean pauseState) {
        this.pauseState = pauseState;
    }
}
