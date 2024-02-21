package com.mygdx.game.Scenes;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Main;
import java.util.HashMap;
import java.util.Map;

// SceneManager class
public class SceneManager {
    // Declare Attributes
    private Map<String, Screen> sceneMap;
    private boolean pauseSceneState;
    private Main gameController;
    private BitmapFont font; // Will be in UIManager in part 2

    // Store all scenes in a hashmap on initialisation and initialise current scene to start scene
    public SceneManager(Main gameController) {
        this.sceneMap = new HashMap<>();
        this.gameController = gameController;
        sceneMap.put("start", new StartScene(gameController));
        sceneMap.put("game", new GameScene(gameController));
        sceneMap.put("end", new EndScene(gameController));
        this.font = new BitmapFont();
    }

    // Load a scene starting with the start scene
    public void initializeScenes() {
        gameController.setScreen(sceneMap.get("start"));
    }

    public void disposeScenes() {
        font.dispose();
    }

    // Get Scene Map
    public Map<String, Screen> getSceneMap() {
        return sceneMap;
    }

    // Set Scene Map
    public void setSceneMap(Map<String, Screen> sceneMap) {
        this.sceneMap = sceneMap;
    }

    // Get Pause Scene State
    public boolean getPauseSceneState() {
        return pauseSceneState;
    }

    // Set Pause Scene State
    public void setPauseSceneState(boolean pauseSceneState) {
        this.pauseSceneState = pauseSceneState;
    }

    // Get Font
    public BitmapFont getFont() {
        return font;
    }

    // Set font
    public void setFont(BitmapFont font) {
        this.font = font;
    }
}
