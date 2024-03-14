package com.mygdx.game.GameLayer.Levels;

// Levels class containing the assets for each level object (will be deserialized from our levels.json)
public class Levels {
    // Declare attributes
    private int levelNumber;
    private String levelTitle;
    private int numberOfEnemies;

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