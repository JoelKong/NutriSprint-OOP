package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Main;
import com.mygdx.game.Scenes.SceneManager;

public class UiManager {
    private Main gameController;
    private Skin skin;
    private Stage stage;

    public UiManager (Main gameController, Skin skin) {
        this.gameController = gameController;
        this.skin = skin;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return this.stage;
    }

    public void createStartButton() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        Button button = new Button(skin);
        mainTable.add(button).center().width(300).height(100);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SceneManager sceneManager = gameController.getSceneManager();
                gameController.setScreen(sceneManager.getSceneMap().get("game"));
            }
        });
    }

    public CustomTextButton createCustomTextButton(String text) {
        CustomTextButton button = new CustomTextButton(text, skin);
        stage.addActor(button);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SceneManager sceneManager = gameController.getSceneManager();
                gameController.setScreen(sceneManager.getSceneMap().get("game"));
            }
        });

        return button;
    }
}
