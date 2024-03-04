package com.mygdx.game.Scenes;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.InputOutput.Inputs;
import com.mygdx.game.Main;

// Start Scene class inherited from Scenes
public class StartScene extends Scenes {
    private Skin skin;
    private Stage stage;

    // Parameterized Constructor setting start scene details
    protected StartScene(Main gameController) {
        super(1, "start", gameController);
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("ui/myskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        Button button = new Button(skin);
        mainTable.add(button).center().width(300).height(100);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Execute the scene change when the button is clicked
                SceneManager sceneManager = getGameController().getSceneManager();
                getGameController().setScreen(sceneManager.getSceneMap().get("game"));
            }
        });
    }

    // Render start scene
    @Override
    public void render(float delta) {
        // Get necessary data
        Inputs preferredControls = getGameController().getInputOutputManager().getPreferredControls();
        SceneManager sceneManager = getGameController().getSceneManager();
        SpriteBatch batch = getGameController().getBatch();

        // Update camera
        getCamera().updateCamera(batch);

        // Upon starting, change scene to game
        if (preferredControls.getStartKey()) {
            getGameController().setScreen(sceneManager.getSceneMap().get("game"));
        }

        // Background
        Gdx.gl.glClearColor(0, 1, 0, 1); // setting clear color to green
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear screen

        // Stage
        stage.getViewport().apply();
        stage.act();
        stage.draw();

        // Text
        batch.begin();
        // renderTextAtScenePosition(batch, "Press 'Enter' to start.", "center");
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    };

    public void dispose() {
        stage.dispose();
    }
}