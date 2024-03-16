package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.badlogic.gdx.Game;

// Manager to handle all forms of UI
public class UiManager {
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;

    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        this.uiStage = new Stage(uiViewport, spriteBatch);
        this.uiTable = new Table();

        // new start button lmao will be refactored again
        Skin startButtonSkin = new Skin(Gdx.files.internal("UI/terra-mother/skin/terra-mother-ui.json"));

        uiTable.setFillParent(true);
        uiTable.center();
        uiStage.addActor(uiTable);

        Gdx.input.setInputProcessor(uiStage);
    }

    // Start the game HUD
    public void startGameHUD() {
        if (uiGameHUD == null) {
            uiGameHUD = new HUD();
        }

        uiStage.addActor(uiGameHUD.getHudTable());
        // uiStage.addActor(uiGameHUD.getHealthbar());
    }

    // Create the UI for start scene
    public void createStartSceneUI(SceneManager sceneManager) {
        createStartButton(sceneManager);
        uiTable.row();
        createEndButton();
    }

    public void createEndSceneUI(SceneManager sceneManager) {
        createRestartButton(sceneManager);
        uiTable.row();
        createMainMenuButton(sceneManager);
        uiTable.row();
        createEndButton();
    }

    public void updateGameHUDLevel(int level) {
        uiGameHUD.updateLevel(level);
    };

    // Creating start button
    private void createStartButton(SceneManager sceneManager) {
        WindowButton startButton = new WindowButton("Start Game", () -> {
            sceneManager.transitionScenes("game");
        });

        uiTable.add(startButton).padBottom(10).center();
    }

    // Creating end button
    private void createEndButton() {
        WindowButton quitButton = new WindowButton("Quit Game", () -> {
            Gdx.app.exit();
        });

        uiTable.add(quitButton).padTop(10).center();
    }

    private void createRestartButton(SceneManager sceneManager) {
        WindowButton restartButton = new WindowButton("Restart from first level", () -> {
            sceneManager.transitionScenes("game");
        });

        uiTable.add(restartButton).padBottom(10).center();
    }

    private void createMainMenuButton(SceneManager sceneManager) {
        WindowButton mainMenuButton = new WindowButton("Return to main menu", () -> {
            sceneManager.transitionScenes("start");
        });

        uiTable.add(mainMenuButton).padTop(10).center();
    }

    // Get UI Stage
    public Stage getUiStage() {
        return uiStage;
    }

    // Set UI Stage
    public void setUiStage(Stage uiStage) {
        this.uiStage = uiStage;
    }

    // Get Table
    public Table getUiTable() {
        return uiTable;
    }

    // Set Table
    public void setUiTable(Table uiTable) {
        this.uiTable = uiTable;
    }

    // Get Game HUD
    public HUD getUiGameHUD() {
        return uiGameHUD;
    }

    // Set Game HUD
    public void setUiGameHUD(HUD uiGameHUD) {
        this.uiGameHUD = uiGameHUD;
    }
}
