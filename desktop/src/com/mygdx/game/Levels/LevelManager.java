package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class LevelManager {
    private int levelNumber;
    private Levels levelsPackage;

    public LevelManager() {
        this.levelNumber = 1;
        Json json = new Json();
        Levels data = json.fromJson(Levels.class, Gdx.files.internal("levels.json"));
        this.levelsPackage = data;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Levels getLevelsPackage() {
        return levelsPackage;
    }

    public void setLevelsPackage(Levels levelsPackage) {
        this.levelsPackage = levelsPackage;
    }
}
