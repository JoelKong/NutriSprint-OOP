package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Controls.PlayerControlManager;
import com.mygdx.game.Entity.EntityManager;
import com.mygdx.game.InputOutput.InputOutputManager;

public class GameScene extends Scenes implements Screen {
    private InputOutputManager inputOutputManager;
    private EntityManager entityManager;
    private PlayerControlManager controlManager;
    private SpriteBatch batch;

    public GameScene() {
        super(2, "gameScreen");
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        entityManager = new EntityManager();
        inputOutputManager = new InputOutputManager();
        controlManager = new PlayerControlManager();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        batch.begin();
//        inputOutputManager.updateKeys();
//        entityManager.drawEntities(batch);
//        batch.end();

//        entityManager.movePlayerEntity(inputOutputManager.getKeyboardMouse(), controlManager.getPlayerControls());
        // Additional render logic, if needed
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

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
