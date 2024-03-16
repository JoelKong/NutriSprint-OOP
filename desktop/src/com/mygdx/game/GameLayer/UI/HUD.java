package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HUD {
    private StyledLabel scoreLabel;
    private StyledLabel levelLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;
    private Healthbar healthbar;

    public HUD() {
        this.uiStage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        // Initialize the hudTable
        this.hudTable = new Table();
        hudTable.setFillParent(true);
        hudTable.top();

        // Initialize the score and level labels
        this.scoreLabel = new StyledLabel("Score: 0");
        this.levelLabel = new StyledLabel("Level: 1");

        // Initialize the health bar with the maximum health
        this.healthbar = new Healthbar(10);

        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(20).expandX();
        hudTable.add(scoreLabel).align(Align.center).padTop(10).expandX();
        hudTable.add(healthbar).align(Align.right).padTop(30).padRight(300).size(240, 24);

        // Add the hudTable to the uiStage
        uiStage.addActor(hudTable);
    }

    public void updateHudScore (int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateHudHealth(int health) {
        // Directly update the healthbar which is now part of the HUD
        healthbar.updateHealth(health);
    }

    public void updateHudLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

    public void draw() {
        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    public void dispose() {
        healthbar.dispose();
        uiStage.dispose();
        skin.dispose();
    }

    // Getters and Setters
    public StyledLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setScoreLabel(StyledLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public StyledLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(StyledLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public void setUiStage(Stage uiStage) {
        this.uiStage = uiStage;
    }

    public Table getHudTable() {
        return hudTable;
    }

    public void setHudTable(Table hudTable) {
        this.hudTable = hudTable;
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(Healthbar healthbar) {
        this.healthbar = healthbar;
    }

}
