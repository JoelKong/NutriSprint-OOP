package com.mygdx.game.EngineLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HUD {
    private StyledLabel scoreLabel;
    private StyledLabel levelLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;
    private Healthbar healthbar; // Now it will be added to the hudTable

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
        this.healthbar = new Healthbar(10); // Assume the max health is 10

        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(20).expandX();
        hudTable.add(scoreLabel).align(Align.center).padTop(10).expandX();
        hudTable.add(healthbar).align(Align.right).padTop(30).padRight(300).size(240, 24);

        // Add the hudTable to the uiStage
        uiStage.addActor(hudTable);
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateHealth(int health) {
        // Directly update the healthbar which is now part of the HUD
        healthbar.updateHealth(health);
    }

    public void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

    public void draw() {
        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    public void dispose() {
        // Dispose the healthbar and other resources
        healthbar.dispose();
        uiStage.dispose();
        skin.dispose();
    }

    public Stage getStage() {
        return uiStage;
    }

    public Table getHudTable() {
        return hudTable;
    }
}
