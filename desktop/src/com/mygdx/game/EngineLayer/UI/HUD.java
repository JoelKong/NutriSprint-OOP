package com.mygdx.game.EngineLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HUD {
    private Label scoreLabel;
    private Label levelLabel;
    private Label healthLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;


    public HUD() {
        this.uiStage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        this.hudTable = new Table();
        hudTable.setFillParent(true);
        hudTable.top();

        this.scoreLabel = new Label("Score: 0", skin);
        this.levelLabel = new Label("Level: 1", skin);
        this.healthLabel = new Label("Health: 10", skin);


        // Add the score and level labels to the hudTable
        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(10).expandX();
        hudTable.add(scoreLabel).align(Align.center).padTop(10).expandX();
        hudTable.add(healthLabel).align(Align.right).padTop(10).padRight(10).expandX();

        // Add the hearts table to the bottom left of the hudTable
        hudTable.row(); // Move to the next row for hearts

        uiStage.addActor(hudTable); // Add the hudTable to the uiStage
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateHealth(int health) {
        healthLabel.setText("Health: " + health);
    }

    public void updateLevel(int level) {
        levelLabel.setText("Level: " + level);
    }

    public void draw() {
        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    public void dispose() {
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
