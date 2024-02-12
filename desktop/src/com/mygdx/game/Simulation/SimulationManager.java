package com.mygdx.game.Simulation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AI.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.ControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Scenes.StartScene;

public class SimulationManager {
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private ControlManager controlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private SpriteBatch batch;

    batch = new SpriteBatch();
    entityManager = new EntityManager();
    sceneManager = new SceneManager();
    inputOutputManager = new InputOutputManager();
    controlManager = new ControlManager();
    aiControlManager = new AIControlManager();
    collisionManager = new CollisionManager();


    public void initialiseSimulation() {
        sceneManager.loadScreen(sceneManager.getStartScreen);
    }

    public void updateSimulation() {
        currentScene = sceneManager.getCurrentScene;
        sceneManager.loadScreen(currentScene);
        if (currentScene instanceof StartScene) {
            controlManager.mapControls(currentScene);
        } else if (currentScene instanceof GameScene) {
            initialisePlayerMovement();
        }
        // collision
    }

    public void endSimulation() {
        //dispose
    }
}
