package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameLayer.Scenes.SceneManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

// Manager to handle all forms of UI
public class UiManager {
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;
    private UIElementFactory uiElementFactory;
    private Skin skin;

    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        this.uiStage = new Stage(uiViewport, spriteBatch);
        this.uiTable = new Table();
        this.uiElementFactory = new UIElementFactory();
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        uiTable.setFillParent(true);
        uiTable.center();
        uiStage.addActor(uiTable);

        Gdx.input.setInputProcessor(uiStage);
    }

    // Start the game HUD
    public void startGameHUD(String[] dialogue) {
        if (uiGameHUD == null) {
            uiGameHUD = new HUD(this.uiStage, dialogue);
        }

        uiStage.addActor(uiGameHUD.getHudTable());
    }

    // Create the UI for start scene
    public void createStartSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton startButton = uiElementFactory.createButton("START", sceneManager, soundManager);
        uiTable.add(startButton).padBottom(10).center();
        uiTable.row();

        WindowButton instructionButton = uiElementFactory.createButton("INSTRUCTION", sceneManager, soundManager);
        uiTable.add(instructionButton).padBottom(10).center();
        uiTable.row();

        WindowButton quitButton = uiElementFactory.createButton("QUIT", sceneManager, soundManager);
        uiTable.add(quitButton).colspan(3).center();
    }

    // Create the UI for instruction scene
    public void createInstructionSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        WindowButton backButton = uiElementFactory.createButton("BACK", sceneManager, soundManager);
        uiTable.add(backButton).align(Align.topLeft).padTop(10).padLeft(20).expandX();
        uiTable.row();

        StyledLabel instructionLabel = new StyledLabel("Avoid the junkies that are chasing you and collect all the fruits and vegetables!");
        uiTable.add(instructionLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        StyledLabel movementLabel = new StyledLabel("Press WASD to move");
        uiTable.add(movementLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        StyledLabel teleportLabel = new StyledLabel("Press SHIFT to teleport");
        uiTable.add(teleportLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        StyledLabel explodeLabel = new StyledLabel("Press SPACE to explode");
        uiTable.add(explodeLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        StyledLabel pauseLabel = new StyledLabel("Press ESC to pause/unpause");
        uiTable.add(pauseLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        StyledLabel progressLabel = new StyledLabel("Press ENTER to interact");
        uiTable.add(progressLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row
    }

    // Create the UI for end scene
    public void createEndSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        uiTable.padTop(Gdx.graphics.getHeight() / 4);

        // Game Over message label
        StyledLabel gameOverLabel = new StyledLabel("The cholesterol has finally gotten a hold of you...");
        uiTable.add(gameOverLabel).colspan(3).padBottom(20).center();
        uiTable.row(); // Move to the next row

        // Read data from binary file
        String filename = "game_data.bin"; // Filename used to store game data
        try {
            // Open file in binary mode
            DataInputStream inputStream = new DataInputStream(new FileInputStream(filename));

            // Read data from the file
            String levelTitle = inputStream.readUTF();
            int playerScore = inputStream.readInt();

            // Close the input stream
            inputStream.close();

            // Create a label to display the retrieved data
            StyledLabel dataLabel = new StyledLabel("Made it to: " + levelTitle + "\n\nTotal fruits collected: " + playerScore);
            uiTable.add(dataLabel).colspan(3).padBottom(20).center();
            uiTable.row(); // Move to the next row
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public void updateGameHudDialogue(String text) {
        uiGameHUD.updateHudDialogue(text);
    }

    public void updateGameHudDialogueVisible() {
        uiGameHUD.updateHudDialogueVisible();
    }

    public void updateGameHudDialogueInvisible() {
        uiGameHUD.updateHudDialogueInvisible();
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
