package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.InputOutput.Inputs;
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
    private enum EntityType {
        PLAYER, AI
    }

    // Default Constructor class to initialise entities
    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.playerEntityList = new ArrayList<>();
        this.AIEntityList = new ArrayList<>();
    }

    private void clearLists() {
        entityMap.clear();
        playerEntityList.clear();
        AIEntityList.clear();
    }

    // Create GameEntity objects
    private GameEntity createEntity(EntityType entityType) {
        switch (entityType) {
            case PLAYER:
                return new Player();
            case AI:
                return new AI();
            default: // Logs a warning message in case of wrong entity type.
                System.out.println("Warning: Unknown entityType when creating a new entity object.");
                return null;
        }
    }

    private void populateEntities(Levels level) throws CloneNotSupportedException {
        playerEntityList.add(createEntity(EntityType.PLAYER));
        for (int i = 0; i < level.getNumberOfEnemies(); i++) {
            AIEntityList.add(createEntity(EntityType.AI).clone());
        }
    }

    // Method to get a list of entities of a specified type and quantity
    public List<GameEntity> getListOfEntities(String entityString, int numberOfEntities) {
        List<GameEntity> entities = new ArrayList<>();

        // Get the EntityType enum corresponding to the entityString
        EntityType entityType = getEntityType(entityString);

        if (entityType != null) {
            // Create the specified number of entities of the given type
            for (int i = 0; i < numberOfEntities; i++) {
                GameEntity entity = createEntity(entityType);
                if (entity != null) {
                    entities.add(entity);
                }
            }
        }

        return entities;
    }

    /* Default Initialization of Entities
    1)clear all lists. 2)Create GameEntities based on level specification 3)Put them into EntityMap.*/
    public void initializeEntities(Levels level) throws CloneNotSupportedException {
        clearLists();
        populateEntities(level);
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

    // Initialising of entity movement
    public void initialiseEntityMovement(Inputs commandInput, PlayerControls playerControls) {
        for (GameEntity entity: playerEntityList) {
            Player player = (Player) entity;
            player.playerMovement(commandInput, playerControls);
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
}