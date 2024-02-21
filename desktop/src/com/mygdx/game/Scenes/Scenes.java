package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.Entity.PlayerControls;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Levels.LevelManager;
import com.mygdx.game.Main;


// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private Main gameController;
    private OrthographicCamera camera; // will go into camera manager

    // Parameterized constructor to specify details of scenes
    protected Scenes(int sceneId, String sceneName, Main gameController) {
        this.gameController = gameController;
        this.camera = new OrthographicCamera();
        this.sceneId = sceneId;
        this.sceneName = sceneName;
    }

    // Render text at specified position (will go to ui manager)
    protected void renderTextAtScenePosition(SpriteBatch batch, String text, String position) {
        float x = 0;
        float y = 0;

        GlyphLayout layout = new GlyphLayout(gameController.getSceneManager().getFont(),text);

        switch (position) {
            case "top":
                x = (Gdx.graphics.getWidth() - layout.width) / 2;
                y = Gdx.graphics.getHeight() - 20;
                break;
            case "topleft":
                x = 10;
                y = Gdx.graphics.getHeight() - 10;
                break;
            case "center":
                x = (Gdx.graphics.getWidth() - layout.width) / 2; // Center horizontally
                y = (Gdx.graphics.getHeight() + layout.height) / 2; // Center vertically
                break;
            default:
                return;
        }
        gameController.getSceneManager().getFont().draw(batch, layout, x, y);
    }

    // Overrides
    public void show() {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    abstract public void render(float delta);

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    };

    public void pause() {};

    public void resume() {};

    public void hide() {};

    public void dispose() {};


    // Get Scene ID
    public int getSceneId() {
        return sceneId;
    }

    // Set Scene ID
    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    // Get Scene Name
    public String getSceneName() {
        return sceneName;
    }

    // Set Scene Name
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    // Get game controller
    public Main getGameController() {
        return gameController;
    }

    // Get Camera
    public OrthographicCamera getCamera() {
        return camera;
    }

    // Set Camera
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
}
