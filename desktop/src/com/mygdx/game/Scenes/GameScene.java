package com.mygdx.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;


import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScene extends Scenes implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private BitmapFont font;
    private GlyphLayout layout;

    public GameScene() {
        super(2, "gameScreen");
        font = new BitmapFont();
        batch = new SpriteBatch();
        layout = new GlyphLayout();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        layout.setText(font, "Press 'P' to pause game.");

        float textWidth = layout.width;
        float textHeight = layout.height;

        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float margin = 20;
        float y = Gdx.graphics.getHeight() - margin;

        batch.begin();
            font.draw(batch, layout, x, y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        layout.setText(font, "Game Paused");

        float textWidth = layout.width;
        float textHeight = layout.height;

        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float margin = 20;
        float y = Gdx.graphics.getHeight() - margin;

        batch.begin();
        font.draw(batch, layout, x, y);
        batch.end();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    // Implement or override other methods as needed, following the same pattern
    // ...

    @Override
    public void dispose() {// Call the superclass dispose method if it contains important logic
        batch.dispose();
        // Dispose of any additional resources specific to GameScene
    }
}
