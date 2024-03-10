package com.mygdx.game.EngineLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;

// Manager to handle all forms of UI
public class UiManager {
    private Stage uiStage;
    private Table uiTable;
    private HUD uiGameHUD;
    private Skin startButtonSkin;

    public UiManager(SpriteBatch spriteBatch, Viewport uiViewport) {
        this.uiStage = new Stage(uiViewport, spriteBatch);
        this.uiTable = new Table();

        // new start button lmao will be refactored again
        this.startButtonSkin = new Skin(Gdx.files.internal("UI/terra-mother/skin/terra-mother-ui.json"));

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

    public void createEndSceneUI(Main gameController) {
        createRestartButton(gameController);
        uiTable.row();
        createMainMenuButton(gameController);
    }

    public void updateGameHUDLevel(int level) {
        uiGameHUD.updateLevel(level);
    };

    // Creating start button
    private void createStartButton(Main gameController) {
        /*CustomTextButton startButton = new CustomTextButton("Start Game", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
        });*/

        // startButton.setSize(200, 100);

        /*ImageTextButton startButton = new ImageTextButton("Start Game", startButtonSkin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
            }
        });*/

        // Load the NinePatch from the .9.png file
        Texture texture = new Texture(Gdx.files.internal("UI/terra-mother/raw/window.9.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);

        // set up ninepatch for converting .9.png image to drawable
        NinePatch patch = new NinePatch(new TextureRegion(texture), 1, 1, 1, 1); // The constructor will override these values with the .9.png content
        Drawable drawable = new NinePatchDrawable(patch);

        // Adjust the font size to better fit the button
        BitmapFont font = new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")); // Load your actual font file here, and set its scale as needed
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); // This may help with font quality

        ImageTextButton.ImageTextButtonStyle textButtonStyle = new ImageTextButton.ImageTextButtonStyle();
        textButtonStyle.up = drawable;
        textButtonStyle.down = drawable;
        textButtonStyle.font = font;

        // Create the TextButton
        ImageTextButton textButton = new ImageTextButton("Start Game", textButtonStyle);

        // padding for button
        textButton.pad(20);
        textButton.padTop(20);
        textButton.padBottom(20);
        textButton.padLeft(25);
        textButton.padRight(25);

        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
            }
        });

        uiTable.add(textButton).padBottom(10).center();
    }

    // Creating end button
    private void createEndButton() {
        CustomTextButton quitButton = new CustomTextButton("End Game", () -> {
            Gdx.app.exit();
        });

        uiTable.add(quitButton).padTop(10).center();
    }

    private void createRestartButton(Main gameController) {
        CustomTextButton restartButton = new CustomTextButton("Restart from first level", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
        });

        uiTable.add(restartButton).padBottom(10).center();
    }

    private void createMainMenuButton(Main gameController) {
        CustomTextButton returnToMenuButton = new CustomTextButton("Return to main menu", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("start"));
        });

        uiTable.add(returnToMenuButton).padTop(10).center();
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
