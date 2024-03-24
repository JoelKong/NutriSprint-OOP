package com.mygdx.game.GameLayer.Camera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// Camera Manager Class
public class CameraManager {
    private OrthographicCamera gameCamera;
    private Viewport cameraViewport;
    private Stage cameraStage;
    private Viewport uiViewport;
    private OrthographicCamera uiCamera;

    // Default constructor to create UI camera and game camera
    public CameraManager() {
        // UI Camera
        this.uiCamera = new OrthographicCamera();
        this.uiViewport = new FitViewport(1980, 1080, uiCamera);

        // Game Camera
        this.gameCamera = new OrthographicCamera();
        this.cameraViewport = new FitViewport(1980, 1080);
        this.cameraStage = new Stage(cameraViewport);
        this.cameraStage.setDebugAll(true);
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

        // Update UI viewport to maintain aspect ratio and center elements
        /*uiViewport.update(width, height, true); // True to center the camera
        uiCamera.position.set(uiViewport.getWorldWidth() / 2, uiViewport.getWorldHeight() / 2, 0);
        uiCamera.update();*/

        cameraStage.getViewport().update(width, height, true);
    }

    // Focus the camera on a position
    public void focusCamera(float x, float y, SpriteBatch batch) {
        // Size of game world
        float worldWidth = Gdx.graphics.getWidth();
        float worldHeight = Gdx.graphics.getHeight();

        // Calculate the half viewport size to correctly clamp the camera's position
        float halfViewportWidth = gameCamera.viewportWidth * 0.5f * gameCamera.zoom;
        float halfViewportHeight = gameCamera.viewportHeight * 0.5f * gameCamera.zoom;

        // Clamp the camera position to ensure it doesn't go beyond the game world boundaries
        x = Math.max(halfViewportWidth, Math.min(worldWidth - halfViewportWidth, x));
        y = Math.max(halfViewportHeight, Math.min(worldHeight - halfViewportHeight, y));

        // Zoom in to the game world
        gameCamera.zoom = 0.4f;

        // Set camera position
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

    // Get Camera Viewport
    public Viewport getCameraViewport() {
        return cameraViewport;
    }

    // Set Camera Viewport
    public void setCameraViewport(Viewport cameraViewport) {
        this.cameraViewport = cameraViewport;
    }

    // Get Camera Stage
    public Stage getCameraStage() {
        return cameraStage;
    }

    // Set Camera Stage
    public void setCameraStage(Stage cameraStage) {
        this.cameraStage = cameraStage;
    }

    // Get UI Viewport
    public Viewport getUiViewport() {
        return uiViewport;
    }

    // Set UI Viewport
    public void setUiViewport(Viewport uiViewport) {
        this.uiViewport = uiViewport;
    }

    // Get UI Camera
    public OrthographicCamera getUiCamera() {
        return uiCamera;
    }

    // Set UI Camera
    public void setUiCamera(OrthographicCamera uiCamera) {
        this.uiCamera = uiCamera;
    }
}
