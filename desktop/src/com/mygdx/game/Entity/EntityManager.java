package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Levels.Levels;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Entity Manager Class
public class EntityManager {
    // Declare Maps and Lists of entities
    private Map<String, List<GameEntity>> entityMap;
    private List<GameEntity> AIEntityList;
    private List<GameEntity> playerEntityList;

    // Default Constructor class to initialise entities
    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.playerEntityList = new ArrayList<>();
        this.AIEntityList = new ArrayList<>();
    }

    // Default Initialization of Entities (clear all lists then pass in level assets respectively)
    public void initializeEntities(Levels level) {
        entityMap.clear();
        playerEntityList.clear();
        AIEntityList.clear();

        playerEntityList.add(new Player());
        for (int i = 0; i < level.getNumberOfEnemies(); i++) {
            AIEntityList.add(new AI());
        }
        entityMap.put("player", playerEntityList);
        entityMap.put("ai", AIEntityList);
    }

    // Drawing of Entities
    public void drawEntities(SpriteBatch sb) {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                if (!entity.getPopFromScreen()) {
                    entity.draw(sb);
                    entity.updateEntityHitbox();
                }
            }
        }
    }

    // Disposing of Entities
    public void disposeEntities() {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                entity.getTexture().dispose();
            }
        }
    }

    // Get Entity Map
    public Map<String, List<GameEntity>> getEntityMap() {
        return entityMap;
    }

    // Set Entity Map
    public void setEntityMap(Map<String, List<GameEntity>> entityMap) {
        this.entityMap = entityMap;
    }

    // Get Player Entity List
    public List<GameEntity> getPlayersList() {
        return playerEntityList;
    }

    // Set Player Entity List
    public void setPlayersList(List<GameEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
    }

    // Get AI Entity List
    public List<GameEntity> getAIEntityList() {
        return AIEntityList;
    }

    // Set AI Entity List
    public void setAIEntityList(List<GameEntity> AIEntityList) {
        this.AIEntityList = AIEntityList;
    }
}