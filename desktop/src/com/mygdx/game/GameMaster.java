package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AI.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.ControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

public class GameMaster extends ApplicationAdapter {
    // Declaring of variables
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private ControlManager controlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private SpriteBatch batch;

    @Override
    public void create() {
        // Loading of Managers and Batch
        batch = new SpriteBatch();
        simulationManager = new SimulationManager();
        entityManager = new EntityManager();
        sceneManager = new SceneManager();
        inputOutputManager = new InputOutputManager();
        controlManager = new ControlManager();
        aiControlManager = new AIControlManager();
        collisionManager = new CollisionManager();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        batch.begin();
            // put here for now for testing
            inputOutputManager.updateKeys();
            entityManager.drawEntities(batch);
        batch.end();

        // Initialise Movement
        entityManager.movePlayerEntity(inputOutputManager.getKeyboardMouse(), controlManager.getPlayerControls());

    }

    @Override
    public void dispose() {
        batch.dispose();
        entityManager.disposeEntities();
    }
}
