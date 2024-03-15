package com.mygdx.game.GameLayer.Scenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.UI.UiManager;


// End scene class inherited from scenes
public class EndScene extends Scenes {
    private SceneManager sceneManager;
    private Game gameController;
    private UiManager uiManager;

    // Parameterized Constructor specifying details of end scene
    protected EndScene(SceneManager sceneManager) {
        super(3, "end");
        this.sceneManager = sceneManager;
        this.gameController = sceneManager.getGameController();;
    }

    // First run of end scene
    @Override
    public void show() {
        //        setSceneBackgroundTexture(new Texture(Gdx.files.internal("")));
        this.uiManager = new UiManager(sceneManager.getBatch(), getCamera().getUiViewport());
//        uiManager.createEndSceneUI(getGameController());
    }

    // Render end scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getInputOutputManager().getPreferredControls();
        SpriteBatch batch = sceneManager.getBatch();

        // Update camera
        getCamera().updateCamera(batch);

        // Restart back to game scene
        if (preferredControls.getRestartKey()) {
            gameController.setScreen(sceneManager.getSceneMap().get("game"));

        // Go back to menu
        } else if (preferredControls.getMenuKey()) {
            gameController.setScreen(sceneManager.getSceneMap().get("start"));
        }

        // Background
        Gdx.gl.glClearColor(1, 0, 0, 1); // setting clear color to red
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        // UI
//        uiManager.getUiStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        uiManager.getUiStage().draw();
    }

    // Get UI manager
    public UiManager uiManager() {
        return uiManager;
    }
}