package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.EngineEntityManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.Levels;
import java.util.*;

// Entity Manager Class
public class EntityManager extends EngineEntityManager {
    // Declare Maps and Lists of entities
    private Map<String, List<GameEntity>> entityMap;
    private List<GameEntity> aiEntityList;
    private List<GameEntity> playerEntityList;
    private enum EntityType {
        PLAYER, AI
    }

    // Default Constructor class to initialise entities
    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.playerEntityList = new ArrayList<>();
        this.aiEntityList = new ArrayList<>();
    }

    // Clear all entity lists\
    @Override
    public void clearEntityLists() {
        playerEntityList.clear();
        aiEntityList.clear();
    }

    // Factory function to create GameEntity objects
    private GameEntity createEntity(EntityType entityType) {
        switch (entityType) {
            case PLAYER:
                return new Player();
            case AI:
                return new AI();
            default:
                System.out.println("Warning: Unknown entityType when creating a new entity object.");
                return null;
        }
    }

    // Populate entities based off level specification
    private void populateEntities(Levels level) throws CloneNotSupportedException {
        playerEntityList.add(createEntity(EntityType.PLAYER));
        for (int i = 0; i < level.getNumberOfEnemies(); i++) {
            aiEntityList.add(Objects.requireNonNull(createEntity(EntityType.AI)).clone());
        }
    }

    /* Default Initialization of Entities
    1) clear all lists. 2) Create GameEntities based on level specification 3) Put them into EntityMap.*/
    public void initializeEntities(Levels level) throws CloneNotSupportedException {
        clearEntityLists();
        populateEntities(level);
        entityMap.put("player", playerEntityList);
        entityMap.put("ai", aiEntityList);
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

    // Initialising of entity movement
    public void initialiseEntityMovement(Inputs commandInput, PlayerControls playerControls) {
        for (GameEntity entity: playerEntityList) {
            Player player = (Player) entity;
            player.playerMovement(commandInput, playerControls);
        }
    }

    // Disposing of Entities
    @Override
    public void disposeEntities() {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                entity.getTexture().dispose();
            }
        }
    }

    // Getter for EntityType enum
    public EntityType getEntityType(String type) {
        // Convert the provided string to uppercase to match enum values
        type = type.toUpperCase();

        // Check if the provided type matches any of the enum values
        try {
            return EntityType.valueOf(type);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided type is not a valid enum value
            System.out.println("Invalid entity type: " + type);
            return null;
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
    public List<GameEntity> getAiEntityList() {
        return aiEntityList;
    }

    // Set AI Entity List
    public void setAiEntityList(List<GameEntity> aiEntityList) {
        this.aiEntityList = aiEntityList;
    }
}