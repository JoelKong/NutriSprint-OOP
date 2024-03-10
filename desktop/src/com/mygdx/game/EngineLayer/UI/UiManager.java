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
    public void createStartSceneUI(Main gameController) {
        createStartButton(gameController);
        uiTable.row();
        createEndButton();
    }

    public void createEndSceneUI(Main gameController) {
        createRestartButton(gameController);
        uiTable.row();
        createMainMenuButton(gameController);
        uiTable.row();
        createEndButton();
    }

    public void updateGameHUDLevel(int level) {
        uiGameHUD.updateLevel(level);
    };

    // Creating start button
    private void createStartButton(Main gameController) {
        WindowButton startButton = new WindowButton("Start Game", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
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

    private void createRestartButton(Main gameController) {
        WindowButton restartButton = new WindowButton("Restart from first level", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("game"));
        });

        uiTable.add(restartButton).padBottom(10).center();
    }

    private void createMainMenuButton(Main gameController) {
        WindowButton mainMenuButton = new WindowButton("Return to main menu", () -> {
            gameController.setScreen(gameController.getSceneManager().getSceneMap().get("start"));
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
