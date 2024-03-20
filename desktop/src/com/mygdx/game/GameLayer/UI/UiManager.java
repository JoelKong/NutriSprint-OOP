package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLayer.Entity.Player;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

// Manager to handle all forms of UI
public class UiManager {
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;

    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        this.uiStage = new Stage(uiViewport, spriteBatch);
        this.uiTable = new Table();

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
    }

    // Create the UI for start scene
    public void createStartSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        createStartButton(sceneManager, soundManager);
        uiTable.row();
        createEndButton();
    }

    public void createEndSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        uiTable.padTop(Gdx.graphics.getHeight() / 4);

        // Game Over message label
        StyledLabel gameOverLabel = new StyledLabel("The cholesterol has finally gotten a hold of you...");
        uiTable.add(gameOverLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        // Level reached and score labels
//        StyledLabel levelLabel = new StyledLabel("Made it to: " + sceneLevelAssets.getLevelTitle());
//        StyledLabel scoreLabel = new StyledLabel("Total fruits collected: " + player.getScore());
//        uiTable.add(levelLabel).colspan(3).padBottom(20).center();
//        uiTable.row(); // Move to the next row
//        uiTable.add(scoreLabel).colspan(3).padBottom(50).center();
//        uiTable.row(); // Move to the next row

        // Add buttons
        createRestartButton(sceneManager, soundManager);
        uiTable.row();
        createMainMenuButton(sceneManager, soundManager);
        uiTable.row();
        createEndButton();
    }

    public void updateGameHUDLevel(String level) {
        uiGameHUD.updateHudLevel(level);
    }

    // Creating start button
    private void createStartButton(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton startButton = new WindowButton("Start Game", () -> {
            soundManager.playSoundEffect("BUTTONCLICK");
            soundManager.stopBackgroundMusic("MENU");
            sceneManager.transitionScenes("game");
        });

        uiTable.add(startButton).padBottom(10).center();
    }

    // Creating end button
    private void createEndButton() {
        WindowButton quitButton = new WindowButton("Quit Game", () -> {
            Gdx.app.exit();
        });

        uiTable.add(quitButton).colspan(3).center();
    }

    private void createRestartButton(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton restartButton = new WindowButton("Restart from first level", () -> {
            soundManager.playSoundEffect("BUTTONCLICK");
            soundManager.stopBackgroundMusic("GAMEOVER");
            sceneManager.transitionScenes("game");
        });

        uiTable.add(restartButton).colspan(3).padBottom(10).center();
    }

    private void createMainMenuButton(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton mainMenuButton = new WindowButton("Return to main menu", () -> {
            soundManager.playSoundEffect("BUTTONCLICK");
            soundManager.stopBackgroundMusic("GAMEOVER");
            sceneManager.transitionScenes("start");
        });

        uiTable.add(mainMenuButton).colspan(3).padBottom(10).center();
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
