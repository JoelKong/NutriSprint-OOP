package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class HUD {
    private Label scoreLabel;
    private Label healthLabel;
    private Label levelLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;


    public HUD(Stage stage) {
        this.uiStage = stage;
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        this.hudTable = new Table();
        hudTable.top();
        hudTable.setFillParent(true);

        scoreLabel = new Label("Score: 0", skin);
        healthLabel = new Label("Health: 3", skin);
        levelLabel = new Label("Level: 1", skin);


        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(10);


        hudTable.add(scoreLabel).align(Align.center).padTop(10);


        hudTable.add(healthLabel).align(Align.right).padTop(10).padRight(10);

        uiStage.addActor(hudTable);
    }


    public void update(float dt) {
        // Update method to handle logic to update the HUD elements
    }

    public Stage getStage() {
        return uiStage;
    }

    public Table getHudTable() { return hudTable; }
}
