package com.mygdx.game.Levels;

// explain in report why only have getters

public class Levels {
    public int level;
    public String levelTitle;
    public int numberOfEnemies;

    public Levels() {};

    public int getLevel() {
        return level;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public int getNumberOfEnemies() {
        return numberOfEnemies;
    }
}