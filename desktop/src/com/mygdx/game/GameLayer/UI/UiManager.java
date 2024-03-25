package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
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
    private String[] instructions = {
            "Avoid the junkies that are chasing you and collect all the fruits and vegetables!",
            "Press 'WASD' to move",
            "Press 'SHIFT' to teleport",
            "Press 'SPACE' to explode",
            "Press 'ESC' to pause/unpause",
            "Press 'Enter' to interact"
    };
    String[] startButtonTitles = {"START", "INSTRUCTION", "QUIT"};
    String[] endButtonTitles = {"RESTART", "MAINMENU", "QUIT"};

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

    public void createStartSceneUI(SceneManager sceneManager, SoundManager soundManager) {
        // Create start screen buttons
        for (String title : startButtonTitles) {
            WindowButton button = uiElementFactory.createButton(title, sceneManager, soundManager);
            uiTable.add(button).padTop(20).row();
        }
    }

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
        pixmap.dispose(); // Dispose Pixmap to avoid memory leaks

        // Initialize the table for instructions and set its background to the grey drawable
        Table instructionTable = new Table();
        instructionTable.setBackground(grayBackground); // Set the background to the dark grey drawable
        instructionTable.setSize(maxWidth, maxHeight); // Set the size of the table to the maximum values
        instructionTable.setPosition(Gdx.graphics.getWidth() / 2f - maxWidth / 2f, Gdx.graphics.getHeight() / 2f - maxHeight / 2f); // Center the table on the screen

        // Initialize the label for instructions
        LabelStyle labelStyle = new LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE);

        // Create a label for each instruction text
        for (String instructionText : instructions) {
            Label instructionLabel = uiElementFactory.createLabel(instructionText, labelStyle);
            instructionLabel.setWrap(true);
            instructionLabel.setAlignment(Align.center);
            instructionTable.add(instructionLabel).width(maxWidth).pad(10).row();
        }
        this.getUiStage().addActor(instructionTable); // Add the instruction table to the stage
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

        LabelStyle labelStyle = new LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE);

        Label gameOverLabel = uiElementFactory.createLabel("The cholesteral has finally gotten a hold of you!", labelStyle);
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

        // Create end screen buttons for each title
        for (String title : endButtonTitles) {
            WindowButton button = uiElementFactory.createButton(title, sceneManager, soundManager);
            endScoreTable.add(button).padTop(20).row();
        }
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
