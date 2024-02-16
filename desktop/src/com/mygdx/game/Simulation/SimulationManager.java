package com.mygdx.game.Simulation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private PlayerControlManager playerControlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private LevelManager levelManager;

    // Initialise all managers when simulation is created
    public SimulationManager() {
        this.sceneManager = new SceneManager();
        this.inputOutputManager = new InputOutputManager();
        this.playerControlManager = new PlayerControlManager();
        this.aiControlManager = new AIControlManager();
        this.collisionManager = new CollisionManager();
        this.entityManager = new EntityManager();
        this.levelManager = new LevelManager();
    }

    // Start the simulation and listen to requests that needs to be looped
    public void startSimulation() {
        sceneManager.initializeScenes(entityManager, collisionManager, aiControlManager, inputOutputManager, playerControlManager, levelManager);
    }

    // Ends the simulation and disposes everything used
    public void endSimulation() {
        for (List<GameEntity> entities: entityManager.getEntityMap().values()) {
            for (GameEntity entity: entities) {
                entity.getTexture().dispose();
            }
        }
        for (Scenes scenes: sceneManager.getSceneMap().values()) {
            scenes.dispose();
        }
    }
}
