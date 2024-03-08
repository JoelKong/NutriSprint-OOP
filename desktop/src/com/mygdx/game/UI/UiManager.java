package com.mygdx.game.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

// UI Manager Class
public class UiManager {
    // Declare attributes
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;

    // Parameterized constructor requiring ui viewport and batch
    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        uiStage = new Stage(uiViewport, spriteBatch);
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiTable.center();
        uiStage.addActor(uiTable);

        Gdx.input.setInputProcessor(uiStage);
    }

    // Start the game HUD
    public void startGameHUD() {
        if (uiGameHUD == null) {
            uiGameHUD = new HUD(uiStage);
        }

        uiStage.addActor(uiGameHUD.getHudTable());
    }

    // Update the game HUD
    private void updateGameHUD() {
        // Update HUD Functions
    }

    // Create the UI for start scene
    public void createStartSceneUI(Main gameController) {
        createStartButton(gameController);
        uiTable.row();
        createEndButton();
    }

    // Creating start button
    private void createStartButton(Main gameController) {
        CustomTextButton startButton = new CustomTextButton("Start Game", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
        });

        uiTable.add(startButton).padBottom(10).center();
    }

    // Creating end button
    private void createEndButton() {
        CustomTextButton quitButton = new CustomTextButton("End Game", () -> {
            Gdx.app.exit();
        });

        uiTable.add(quitButton).padTop(10).center();
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
