package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;


import com.mygdx.game.AI.AIControlManager;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Scenes.GameScene;
import com.mygdx.game.Scenes.SceneManager;
import com.mygdx.game.Simulation.SimulationManager;

import com.mygdx.game.Scenes.StartScene;
import com.mygdx.game.Scenes.EndScene;


public class GameMaster extends ApplicationAdapter {
    // Declaring of variables
    private SimulationManager simulationManager;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputOutputManager inputOutputManager;
    private PlayerControlManager controlManager;
    private AIControlManager aiControlManager;
    private CollisionManager collisionManager;
    private SpriteBatch batch;

    // screens
    private StartScene startScene;
    private GameScene gameScene;
    private EndScene endScene;
    private boolean gameEnd = true;
    private Screen currentScene;

    @Override
    public void create() {
        // Loading of Managers and Batch
//        batch = new SpriteBatch();
        simulationManager = new SimulationManager();
//        entityManager = new EntityManager();
//        sceneManager = new SceneManager();
//        inputOutputManager = new InputOutputManager();
//        controlManager = new PlayerControlManager();
//        aiControlManager = new AIControlManager();
//        collisionManager = new CollisionManager();
//
//        // Screens
//        startScene = new StartScene();
//        gameScene = new GameScene();
//        gameScene.show();
//        endScene = new EndScene();
//        currentScene = startScene;
    }

    @Override
    public void render() {
        simulationManager.startSimulation();
        // commented out the current logic to test out the screens - dinie

        /* ScreenUtils.clear(0, 0, 0.2f, 1);
        batch.begin();
        // put here for now for testing
        inputOutputManager.updateKeys();
        entityManager.drawEntities(batch);
        batch.end();

        // Initialise Movement
        entityManager.movePlayerEntity(inputOutputManager.getKeyboardMouse(), controlManager.getPlayerControls()); */

//        float delta = Gdx.graphics.getDeltaTime();
//
//
//        // game scene
//        gameScene.render(delta);
//
//        // start scene to end scene
//        if (currentScene == startScene && Gdx.input.isKeyJustPressed(Input.Keys.N)) {
//            // transition from the green screen (startScene) to the red screen (endScene)
//            currentScene = endScene;
//        }
//
//        if (currentScene != null) {
//            currentScene.render(delta);
//        }

    }

    @Override
    public void dispose() {
        /* batch.dispose();
        entityManager.disposeEntities(); */
//        if (startScene != null) startScene.dispose();
//        if (endScene != null) endScene.dispose();
//        if (gameScene != null) gameScene.dispose();
//        if (batch != null) batch.dispose();
    }
}
