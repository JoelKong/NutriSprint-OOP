package com.mygdx.game.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// Camera Manager Class
public class CameraManager {
    // Declare Attributes
    private OrthographicCamera gameCamera;
    private Viewport uiViewport;
    private Stage uiStage;

    // Default constructor to create camera
    public CameraManager() {
        this.gameCamera = new OrthographicCamera();
        this.uiViewport = new ScreenViewport();
        this.uiStage = new Stage(uiViewport);
        gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    // Update the game world camera
    public void updateCamera(SpriteBatch batch) {
        gameCamera.update();
        batch.setProjectionMatrix(gameCamera.combined);
    }

    // Upon window resize reset the game world camera and ui camera
    public void resetCameraOnResize(int width, int height) {
        gameCamera.viewportWidth = width;
        gameCamera.viewportHeight = height;
        gameCamera.position.set(width / 2f, height / 2f, 0);
        gameCamera.update();

        uiStage.getViewport().update(width, height, true);    // dinie, stage will be fixed to screen, this updating is for resize
    }

    // Focus the camera on a position
    public void focusCamera(float x, float y, SpriteBatch batch) {
        gameCamera.position.set(x, y, 0);
        gameCamera.update();
        batch.setProjectionMatrix(gameCamera.combined);
    }

    // Get Camera
    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }

    // Set Camera
    public void setGameCamera(OrthographicCamera gameCamera) {
        this.gameCamera = gameCamera;
    }

    // Get UI Viewport
    public Viewport getUiViewport() {
        return uiViewport;
    }

    // Set UI Viewport
    public void setUiViewport(Viewport uiViewport) {
        this.uiViewport = uiViewport;
    }

    // Get UI Stage
    public Stage getUiStage() {
        return uiStage;
    }

    // Set UI Stage
    public void setUiStage(Stage uiStage) {
        this.uiStage = uiStage;
    }
}
