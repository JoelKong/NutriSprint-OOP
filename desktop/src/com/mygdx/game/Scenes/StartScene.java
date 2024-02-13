package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes implements Screen {

    public StartScene() {
        super(1, "startScene");
    }

    @Override
    public void show() {
        // Call to super if needed, and any additional show logic
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen
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
        // Any dispose logic
    }
}