package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.UI.Button; // Make sure to import your Button class

public class UiManager {
    private Stage stage;

    public UiManager() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create and add a custom button
        addButton("Hello", Color.BLUE, Color.FIREBRICK, 100, 50, 100, 100);
    }

    private void addButton(String text, Color upColor, Color downColor, float width, float height, float posX, float posY) {
        Button button = new Button(text, upColor, downColor, width, height);
        button.setPosition(posX, posY);
        // Optionally add listeners here if not done within the Button class
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle button click
                System.out.println(text + " button clicked!");
            }
        });
        stage.addActor(button);
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Optional, clear screen
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }
}
