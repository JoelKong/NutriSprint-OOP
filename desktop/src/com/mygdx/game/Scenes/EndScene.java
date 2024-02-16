package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class EndScene extends Scenes implements Screen {
    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batch;
    private GlyphLayout layout;

    public EndScene() {
        super(3, "endScene");
        font = new BitmapFont();
        batch = new SpriteBatch();
        layout = new GlyphLayout();
    }

    @Override
    public void show() {
        // Call to super if needed, and any additional show logic
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        layout.setText(font, "Game Ended");

        float textWidth = layout.width;
        float textHeight = layout.height;

        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float y = (Gdx.graphics.getHeight() - textHeight) / 2;

        batch.begin();
            font.draw(batch, layout, x, y);
        batch.end();

        // Call render from Scene if needed and any additional render logic
    }

    // Implement or override other methods from Scene if needed

    @Override
    public void resize(int width, int height) {
        // Any resize logic
    }

    @Override
    public void pause() {
        // Any pause logic
    }

    @Override
    public void resume() {
        // Any resume logic
    }

    @Override
    public void hide() {
        // Any hide logic
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}