package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

        // uiStage.setDebugAll(true);

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
        float maxWidth = 600;
        float maxHeight = 400;

        // Creating new table and 'Back' button and aligning it to the top left
        Table backButtonTable = new Table();
        backButtonTable.setFillParent(true);
        backButtonTable.top().left().pad(10);
        WindowButton backButton = uiElementFactory.createButton("BACK", sceneManager, soundManager);
        backButtonTable.add(backButton).align(Align.topLeft);
        this.getUiStage().addActor(backButtonTable);

        // Setting grey background color
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888); // Using Pixmap to paint and fill the color
        pixmap.setColor(Color.DARK_GRAY); // Set the color for the Pixmap
        pixmap.fill(); // Fill the pixmap with the selected color

        // Create a Texture from Pixmap
        TextureRegionDrawable grayBackground = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose(); // It's important to dispose of the Pixmap to avoid memory leaks

        // Initialize the table for instructions and set its background to the grey drawable
        Table instructionTable = new Table();
        instructionTable.setBackground(grayBackground); // Set the background to the dark grey drawable
        instructionTable.setSize(maxWidth, maxHeight); // Set the size of the table to the maximum values
        instructionTable.setPosition(Gdx.graphics.getWidth() / 2f - maxWidth / 2f, Gdx.graphics.getHeight() / 2f - maxHeight / 2f); // Center the table on the screen

        // Initialize the label for instructions
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE);

        Label instructionLabel = new Label("Avoid the junkies that are chasing you and collect all the fruits and vegetables!", labelStyle);
        instructionLabel.setWrap(true); // Enable text wrapping for the label
        instructionLabel.setAlignment(Align.center); // Center the text within the label

        Label movementLabel = new Label("Press 'WASD' to move", labelStyle);
        movementLabel.setWrap(true); // Enable text wrapping for the label
        movementLabel.setAlignment(Align.center); // Center the text within the label

        Label teleportLabel = new Label("Press 'SHIFT' to teleport", labelStyle);
        teleportLabel.setWrap(true); // Enable text wrapping for the label
        teleportLabel.setAlignment(Align.center); // Center the text within the label

        Label explodeLabel = new Label("Press 'SPACE' to explode", labelStyle);
        explodeLabel.setWrap(true); // Enable text wrapping for the label
        explodeLabel.setAlignment(Align.center); // Center the text within the label

        Label escapeLabel = new Label("Press 'ESC' to pause/unpause", labelStyle);
        escapeLabel.setWrap(true); // Enable text wrapping for the label
        escapeLabel.setAlignment(Align.center); // Center the text within the label

        Label interactLabel = new Label("Press 'Enter' to interact", labelStyle);
        interactLabel.setWrap(true); // Enable text wrapping for the label
        interactLabel.setAlignment(Align.center); // Center the text within the label

        // Add the label to the instruction table and configure it to wrap within the max width
        instructionTable.add(instructionLabel).width(maxWidth).pad(10);
        instructionTable.row();
        instructionTable.add(movementLabel).width(maxWidth).pad(10);
        instructionTable.row();
        instructionTable.add(teleportLabel).width(maxWidth).pad(10);
        instructionTable.row();
        instructionTable.add(explodeLabel).width(maxWidth).pad(10);
        instructionTable.row();
        instructionTable.add(escapeLabel).width(maxWidth).pad(10);
        instructionTable.row();
        instructionTable.add(interactLabel).width(maxWidth).pad(10);

        this.getUiStage().addActor(instructionTable); // Add the instruction table to the stage
    }

    // Create the UI for end scene
    public void createEndSceneUIHehe(SceneManager sceneManager, SoundManager soundManager) {
        float maxWidth = 600;
        float maxHeight = 400;

        // uiTable.padTop(Gdx.graphics.getHeight() / 4);

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

    public void createEndSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        float maxWidth = 400;
        float maxHeight = 400;

        // Setting grey background color
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888); // Using Pixmap to paint and fill the color
        pixmap.setColor(Color.DARK_GRAY); // Set the color for the Pixmap
        pixmap.fill(); // Fill the pixmap with the selected color

        // Create a Texture from Pixmap
        TextureRegionDrawable grayBackground = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose(); // It's important to dispose of the Pixmap to avoid memory leaks

        // Initialize the table for instructions and set its background to the grey drawable
        Table endScoreTable = new Table();
        endScoreTable.setBackground(grayBackground); // Set the background to the dark grey drawable
        endScoreTable.setSize(maxWidth, maxHeight); // Set the size of the table to the maximum values
        endScoreTable.setPosition(Gdx.graphics.getWidth() / 2f - maxWidth / 2f, Gdx.graphics.getHeight() / 2f - maxHeight / 2f - 160); // Center the table on the screen

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE);

        Label gameOverLabel = new Label("The cholesteral has finally gotten a hold of you!", labelStyle);
        endScoreTable.add(gameOverLabel).padBottom(20);
        endScoreTable.row();

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
            Label dataLabel = new Label("Made it to: " + levelTitle + "\n\nTotal fruits collected: " + playerScore, labelStyle);
            endScoreTable.add(dataLabel);
            endScoreTable.row(); // Move to the next row
        } catch (IOException e) {
            e.printStackTrace();
        }

        endScoreTable.row().padTop(20);
        WindowButton restartButton = uiElementFactory.createButton("RESTART", sceneManager, soundManager);
        endScoreTable.add(restartButton);
        endScoreTable.row();

        endScoreTable.row().padTop(20);
        WindowButton mainMenuButton = uiElementFactory.createButton("MAINMENU", sceneManager, soundManager);
        endScoreTable.add(mainMenuButton);
        endScoreTable.row();

        endScoreTable.row().padTop(20);
        WindowButton quitButton = uiElementFactory.createButton("QUIT", sceneManager, soundManager);
        endScoreTable.add(quitButton);
        endScoreTable.row();

        this.getUiStage().addActor(endScoreTable);
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
