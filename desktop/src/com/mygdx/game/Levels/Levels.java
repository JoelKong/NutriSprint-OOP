package com.mygdx.game.Levels;

// Levels class containing the assets for each level object
public class Levels {
    // Declare attributes
    public int levelNumber;
    public String levelTitle;
    public int numberOfEnemies;

    // Default Constructor
    protected Levels() {};

    // Get Level Number
    public int getLevelNumber() {
        return levelNumber;
    }

    // Get Level Title
    public String getLevelTitle() {
        return levelTitle;
    }

    // Get Number of Enemies
    public int getNumberOfEnemies() {
        return numberOfEnemies;
    }
}