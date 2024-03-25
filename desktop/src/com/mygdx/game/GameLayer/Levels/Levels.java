package com.mygdx.game.GameLayer.Levels;
import java.util.List;

// Levels class containing the assets for each level object (will be deserialized from our levels.json)
public class Levels {
    // Declare attributes
    private int levelNumber;
    private boolean lastLevel;
    private String levelTitle;
    private String levelMusic;
    private String levelBackground;
    private String[] dialogue;
    private int numberOfFries;
    private int numberOfBurgers;
    private int numberOfRocks;
    private int numberOfApples;
    private int numberOfBananas;
    private int numberOfCherries;
    private int numberOfVegetables;
    private String scoreNeeded;
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

    // Get whether it is last level
    public boolean getLastLevel() {
        return lastLevel;
    }

    // Get Level Title
    public String getLevelTitle() {
        return levelTitle;
    }

    // Get Level Music
    public String getLevelMusic() {
        return levelMusic;
    }

    // Get Level Background
    public String getLevelBackground() {
        return levelBackground;
    }

    // Get dialogue
    public String[] getDialogue() {
        return dialogue;
    }

    // Get Number of Fries
    public int getNumberOfFries() {
        return numberOfFries;
    }

    // Get Number of Burgers
    public int getNumberOfBurgers() {
        return numberOfBurgers;
    }

    // Get Number of Rocks
    public int getNumberOfRocks() {
        return numberOfRocks;
    }

    // Get Number of Apples
    public int getNumberOfApples() {
        return numberOfApples;
    }

    // Get Number of Bananas
    public int getNumberOfBananas() {
        return numberOfBananas;
    }

    // Get Number of Cherries
    public int getNumberOfCherries() {
        return numberOfCherries;
    }

    // Get Number of Vegetables
    public int getNumberOfVegetables() {
        return numberOfVegetables;
    }

    // Get the score needed to win
    public String getScoreNeeded() {
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

    // Get Player Texture
    public List<String> getPlayerTexture() {
        return playerTexture;
    }

    // Get Enemy Texture
    public List<String> getEnemyTexture() {
        return enemyTexture;
    }

    // Get Player speed
    public float getPlayerSpeed() {
        return playerSpeed;
    }

    // Get Prop Texture
    public List<String> getPropTexture() {
        return propTexture;
    }
}