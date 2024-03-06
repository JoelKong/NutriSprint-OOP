package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UiManager {
    private Stage uiStage;
    private Viewport uiViewport;
    private OrthographicCamera uiCamera;
    private Table table;

    public UiManager(SpriteBatch spriteBatch) {
        this.uiCamera = new OrthographicCamera();
        this.uiViewport = new ScreenViewport(uiCamera);
        uiStage = new Stage(uiViewport, spriteBatch);

        table = new Table();
        table.setFillParent(true);
        table.center();

        createStartSceneUI();

        uiStage.addActor(table);

        Gdx.input.setInputProcessor(uiStage);
    }

    private void createStartSceneUI() {
        createStartButton();
        // Add a row to the table for the next button
        table.row();
        createEndButton();
    }

    private void createStartButton() {
        CustomTextButton startButton = new CustomTextButton("Start Game"); // Pass the skin to the button

        // Adjust the method of adding the button to the table
        table.add(startButton).padBottom(10).center();
    }

    private void createEndButton() {
        CustomTextButton quitButton = new CustomTextButton("End Game"); // Pass the skin to the button

        // Adjust the method of adding the button to the table
        table.add(quitButton).padTop(10).center();
    }

    public Stage getUiStage() {
        return uiStage;
    }
}
