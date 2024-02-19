package com.mygdx.game.Scenes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Collisions.CollisionManager;
import com.mygdx.game.Entity.PlayerControlManager;
import com.mygdx.game.Entity.AIControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;
import com.mygdx.game.Levels.LevelManager;


// Abstract class Scene implementing LibGDX Screen interface
public abstract class Scenes {
    // Declare variables
    private int sceneId;
    private String sceneName;
    private BitmapFont font; // Will be in UIManager if we choose to create it in part 2

    // Parameterized constructor to specify details of scenes
    public Scenes(int sceneId, String sceneName) {
        this.sceneId = sceneId;
        this.sceneName = sceneName;
        this.font = new BitmapFont(); // Will be in UIManager if we choose to create it in part 2
    }

    // Render text at specified position
    protected void renderTextAtScenePosition(SpriteBatch batch, String text, String position) {
        float x = 0;
        float y = 0;

        GlyphLayout layout = new GlyphLayout(font, text);

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

            font.draw(batch, layout, x, y);
    }

    // Dispose scenes
    public void dispose(SpriteBatch batch) {
            batch.dispose();
            font.dispose(); // will be migrated to UIManager in the future
    }

    // Render overload (GameScene)
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, CollisionManager collisionManager, AIControlManager aiControlManager,
                                InputOutputManager inputOutputManager, PlayerControlManager playerControlManager, LevelManager levelManager) {};

    // Render overload (Start and End Scene)
    public void render(SceneManager sceneManager, SpriteBatch batch, EntityManager entityManager, InputOutputManager inputOutputManager, LevelManager levelManager) {};

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
}
