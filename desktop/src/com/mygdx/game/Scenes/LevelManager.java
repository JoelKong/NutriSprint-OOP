package com.mygdx.game.Scenes;

public class LevelManager {
    private int levelNumber;
    private Object levelsPackage;

    public LevelManager() {
        this.levelNumber = 1;
        this.levelsPackage = null; // for now till we make package
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public Object getLevelsPackage() {
        return levelsPackage;
    }

    public void setLevelsPackage(Object levelsPackage) {
        this.levelsPackage = levelsPackage;
    }
}
