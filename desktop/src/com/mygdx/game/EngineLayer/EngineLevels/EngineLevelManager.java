package com.mygdx.game.EngineLayer.EngineLevels;
import java.util.List;

public  class EngineLevelManager {
    protected int levelNumber;
    protected List<EngineLevel> EngineLevelList;
    public EngineLevel retrieveLevelAssets() {
        for (EngineLevel level: EngineLevelList) {
            if (level.getLevelNumber() == levelNumber); {
                return level;
            }
        }
        return null;
    }

    public boolean levelCleared() {
        return false;
    };

    // Get Level Number
    public int getLevelNumber() {
        return levelNumber;
    }

}
