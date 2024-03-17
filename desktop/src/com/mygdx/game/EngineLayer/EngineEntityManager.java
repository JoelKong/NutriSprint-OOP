package com.mygdx.game.EngineLayer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Entity.GameEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EngineEntityManager {
    // Declare Maps and Lists of entities
    private Map<String, List<GameEntity>> entityMap;
    private List<GameEntity> aiEntityList;
    private List<GameEntity> playerEntityList;

    // Initialise all forms of entity lists
    public EngineEntityManager() {
        this.entityMap = new HashMap<>();
        this.playerEntityList = new ArrayList<>();
        this.aiEntityList = new ArrayList<>();
        entityMap.put("ai", aiEntityList);
        entityMap.put("player", playerEntityList);
    }

    // All games should have the ability to reset, initialize, draw and dispose entities and enabling the entity action
    public void resetEntities() {
        aiEntityList.clear();
        playerEntityList.clear();
    };

    public void initializeEntities() {};

    public abstract void drawEntities(SpriteBatch batch);

    public abstract void disposeEntities();

    public void initialiseEntityActions() {};

    public Map<String, List<GameEntity>> getEntityMap() {
        return entityMap;
    }

    public void setEntityMap(Map<String, List<GameEntity>> entityMap) {
        this.entityMap = entityMap;
    }

    public List<GameEntity> getAiEntityList() {
        return aiEntityList;
    }

    public void setAiEntityList(List<GameEntity> aiEntityList) {
        this.aiEntityList = aiEntityList;
    }

    public List<GameEntity> getPlayerEntityList() {
        return playerEntityList;
    }

    public void setPlayerEntityList(List<GameEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }
}
