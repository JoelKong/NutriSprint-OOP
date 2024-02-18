package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;

import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;


// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes implements Screen {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private BitmapFont font;
    private SpriteBatch batch;

    // Parameterized constructor to specify details of scenes
    public Scenes(int sceneId, String sceneName) {
        this.sceneId = sceneId;
        this.sceneName = sceneName;
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
    }

    // Render overloads
    public void render(SceneManager sceneManager, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {};

    public void render(SceneManager sceneManager, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {};

    public void render(float delta) {};

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

    protected void renderTextAtTop(SpriteBatch batch, String text) {
        if (batch == null || font == null) return;

        GlyphLayout layout = new GlyphLayout(font, text);

        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = Gdx.graphics.getHeight() - 20;

        batch.begin();
            font.draw(batch, layout, x, y);
        batch.end();
    }

    protected void renderTextCentered(SpriteBatch batch, String text) {
        if (batch == null || font == null) return;

        GlyphLayout layout = new GlyphLayout(font, text);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2; // Center horizontally
        float y = (Gdx.graphics.getHeight() + layout.height) / 2; // Center vertically

        batch.begin();
            font.draw(batch, layout, x, y);
        batch.end();
    }

    protected void renderLevelTitleText(SpriteBatch batch, String text) {
        if (batch == null || font == null) return;

        GlyphLayout layout = new GlyphLayout(font, text);

        batch.begin();
            font.draw(batch, text, 10, Gdx.graphics.getHeight() - 10);
        batch.end();
    }

    public void dispose() {
        // Dispose of the SpriteBatch if it's not needed anymore
        if (batch != null) {
            batch.dispose();
        }

        // Dispose of the BitmapFont
        if (font != null) {
            font.dispose();
        }

        // Call dispose on any other disposable resources
    }
}
