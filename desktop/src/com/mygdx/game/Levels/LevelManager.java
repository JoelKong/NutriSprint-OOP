package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import java.util.List;

public class LevelManager {
    private int levelNumber;
    private List<Levels> levelsPackage;

    public LevelManager() {
        this.levelNumber = 1;
        Json json = new Json();
        this.levelsPackage = json.fromJson(List.class, Levels.class, Gdx.files.internal("levels.json"));
    }

    public Levels retrieveCurrentLevelAssets() {
        for (Levels level: levelsPackage) {
            if (level.level == levelNumber) {
                return level;
            }
        }
        return null;
    }

    public boolean doesNextLevelExist() {
        for (Levels level: levelsPackage) {
            if (level.level == levelNumber + 1) {
                return true;
            }
        }
        return false;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public List<Levels> getLevelsPackage() {
        return levelsPackage;
    }

    public void setLevelsPackage(List<Levels> levelsPackage) {
        this.levelsPackage = levelsPackage;
    }
}
