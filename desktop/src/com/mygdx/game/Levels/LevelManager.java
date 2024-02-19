package com.mygdx.game.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import java.util.List;

// Level Manager Class
public class LevelManager {
    // Declare attributes
    private int levelNumber;
    private List<Levels> levelsList;

    // Constructor to initialise level to 1 and load all the raw data from our levels.json
    public LevelManager() {
        this.levelNumber = 1;
        Json json = new Json();
        this.levelsList = json.fromJson(List.class, Levels.class, Gdx.files.internal("levels.json"));
    }

    // Retrieve level assets
    public Levels retrieveLevelAssets() {
        for (Levels level: levelsList) {
            if (level.getLevelNumber() == levelNumber) {
                return level;
            }
        }
        return null;
    }

    // Check if next level exists
    public boolean nextLevelExists() {
        for (Levels level: levelsList) {
            if (level.getLevelNumber() == levelNumber + 1) {
                return true;
            }
        }
        return false;
    }


    // Get Level Number
    public int getLevelNumber() {
        return levelNumber;
    }

    // Set Level Number
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    // Get Levels List
    public List<Levels> getLevelsList() {
        return levelsList;
    }

    // Set Levels List
    public void setLevelsList(List<Levels> levelsPackage) {
        this.levelsList = levelsPackage;
    }
}
