package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

// Manager to handle all forms of UI
public class UiManager {
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;
    private UIElementFactory uiElementFactory;

    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        this.uiStage = new Stage(uiViewport, spriteBatch);
        this.uiTable = new Table();
        this.uiElementFactory = new UIElementFactory();

        uiTable.setFillParent(true);
        uiTable.center();
        uiStage.addActor(uiTable);

        Gdx.input.setInputProcessor(uiStage);
    }

    // Start the game HUD
    public void startGameHUD() {
        if (uiGameHUD == null) {
            uiGameHUD = new HUD(this.uiStage);
        }

        uiStage.addActor(uiGameHUD.getHudTable());
    }

    // Create the UI for start scene
    public void createStartSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton startButton = uiElementFactory.createButton("START", sceneManager, soundManager);
        uiTable.add(startButton).padBottom(10).center();
        uiTable.row();

        WindowButton quitButton = uiElementFactory.createButton("QUIT", sceneManager, soundManager);
        uiTable.add(quitButton).colspan(3).center();
    }

    public void createEndSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        uiTable.padTop(Gdx.graphics.getHeight() / 4);

        // Game Over message label
        StyledLabel gameOverLabel = new StyledLabel("The cholesterol has finally gotten a hold of you...");
        uiTable.add(gameOverLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        // Add buttons
        WindowButton restartButton = uiElementFactory.createButton("RESTART", sceneManager, soundManager);
        uiTable.add(restartButton).colspan(3).padBottom(10).center();
        uiTable.row();

        WindowButton mainMenuButton = uiElementFactory.createButton("MAINMENU", sceneManager, soundManager);
        uiTable.add(mainMenuButton).colspan(3).padBottom(10).center();
        uiTable.row();

        WindowButton quitButton = uiElementFactory.createButton("QUIT", sceneManager, soundManager);
        uiTable.add(quitButton).colspan(3).center();
    }

    public void updateGameHUDLevel(String level) {
        uiGameHUD.updateHudLevel(level);
    }

    public void updateGameHudObjective(String objective) {
        uiGameHUD.updateHudObjective(objective);
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
