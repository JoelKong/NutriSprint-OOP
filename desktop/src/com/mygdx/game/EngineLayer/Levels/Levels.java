package com.mygdx.game.EngineLayer.Levels;
import java.util.List;
import java.util.Map;

// Levels class containing the assets for each level object (will be deserialized from our levels.json)
public class Levels {
    // Declare attributes
    private int levelNumber;
    private String levelTitle;
    private int numberOfEnemies;
    private int numberOfRocks;
    private int numberOfApples;
    private int scoreNeeded;
    private List<String> respawnables;
    private float playerSpeed;
    private float enemySpeed;
    private List<String> playerTexture;
    private List<String> enemyTexture;
    private List<String> propTexture;

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

    // Get Number of Rocks
    public int getNumberOfRocks() {
        return numberOfRocks;
    }

    // Get Number of Apples
    public int getNumberOfApples() {
        return numberOfApples;
    }

    // Get the score needed to win
    public int getScoreNeeded() {
        return scoreNeeded;
    }

    // Get Respawnables
    public List<String> getRespawnables() {
        return respawnables;
    }

    // Get Enemy Speed
    public float getEnemySpeed() {
        return enemySpeed;
    }

    public List<String> getPlayerTexture() {
        return playerTexture;
    }

    public List<String> getEnemyTexture() {
        return enemyTexture;
    }

    public float getPlayerSpeed() {
        return playerSpeed;
    }

    public List<String> getPropTexture() {
        return propTexture;
    }
}