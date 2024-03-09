package com.mygdx.game.EngineLayer.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.EngineLayer.Entity.AI;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;

import java.util.List;
import java.util.Map;

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

    // Generic function to check completion of level
    public boolean levelCleared(List<GameEntity> playerList) {
        for (GameEntity playerEntity : playerList) {
            Player player = (Player) playerEntity;
            return player.getWinStatus();
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
