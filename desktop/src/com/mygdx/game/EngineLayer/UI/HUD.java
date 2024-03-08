package com.mygdx.game.EngineLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

// Game HUD Class
public class HUD {
    private Label scoreLabel;
    private Label healthLabel;
    private Label levelLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;

    // Parameterized constructor to initialise game HUD
    protected HUD(Stage stage) {
        this.uiStage = stage;
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        this.hudTable = new Table();
        hudTable.top();
        hudTable.setFillParent(true);

        scoreLabel = new Label("Score:", skin);
        healthLabel = new Label("Health:", skin);
        levelLabel = new Label("Level:", skin);

        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(10);
        hudTable.add(scoreLabel).align(Align.center).padTop(10);
        hudTable.add(healthLabel).align(Align.right).padTop(10).padRight(10);
        uiStage.addActor(hudTable);
    }

    // Update HUD elements
    protected void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

    public Stage getStage() {
        return uiStage;
    }

    public Table getHudTable() { return hudTable; }
}
