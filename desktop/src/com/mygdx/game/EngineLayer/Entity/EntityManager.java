package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Effects.EffectManager;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.Levels.Levels;

import java.util.*;

// Entity Manager Class
public class EntityManager {
    // Declare Maps and Lists of entities
    private Map<String, List<GameEntity>> entityMap;
    private List<GameEntity> aiEntityList;
    private List<GameEntity> playerEntityList;
    private List<GameEntity> propEntityList;
    private float timeSinceLastSpawn;
    private enum EntityType {
        ISAAC, FRENCHFRIES, ROCK, APPLE, BANANA, BURGER, CHICKEN, CHERRY
    }

    // Default Constructor class to initialise entities
    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.playerEntityList = new ArrayList<>();
        this.aiEntityList = new ArrayList<>();
        this.propEntityList = new ArrayList<>();
        entityMap.put("ai", aiEntityList);
        entityMap.put("player", playerEntityList);
        entityMap.put("props", propEntityList);
    }

    // Clear all entity lists
    public void clearEntityLists() {
        playerEntityList.clear();
        aiEntityList.clear();
        propEntityList.clear();
    }

    // Factory function to create GameEntity objects
    public GameEntity createEntity(EntityType entityType, Levels level) {
        switch (entityType) {
            case ISAAC:
                return new Isaac(level);
            case FRENCHFRIES:
                return new FrenchFries(level);
            case ROCK:
                return new Rock(level);
            case APPLE:
                return new Apple(level);
            case BANANA:
                return new Banana(level);
            case BURGER:
                return new Burger(level);
            case CHICKEN:
                return new Chicken(level);
            case CHERRY:
                return new Cherry(level);
            default:
                System.out.println("Warning: Unknown entityType when creating a new entity object.");
                return null;
        }
    }

    // Populate entities based off level specification
    public void populateEntities(Levels level) throws CloneNotSupportedException {
        playerEntityList.add(createEntity(EntityType.ISAAC, level));

        for (int i = 0; i < level.getNumberOfFries(); i++) {
            GameEntity entity = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.FRENCHFRIES, level)).clone(), entityMap);
            aiEntityList.add(entity);
        }

        for (int i = 0; i < level.getNumberOfBurgers(); i++) {
            GameEntity entity = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.BURGER, level)).clone(), entityMap);
            aiEntityList.add(entity);
        }

        for (int i = 0; i < level.getNumberOfRocks(); i++) {
            GameEntity rock = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.ROCK, level)).clone(), entityMap);
            propEntityList.add(rock);
        }

        for (int i = 0; i < level.getNumberOfApples(); i++) {
            GameEntity apple = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.APPLE, level)).clone(), entityMap);
            propEntityList.add(apple);
        }

        for (int i = 0; i < level.getNumberOfBananas(); i++) {
            GameEntity banana = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.BANANA, level)).clone(), entityMap);
            propEntityList.add(banana);
        }

        for (int i = 0; i < level.getNumberOfCherries(); i++) {
            GameEntity banana = randomiseEntityPosition(Objects.requireNonNull(createEntity(EntityType.CHERRY, level)).clone(), entityMap);
            propEntityList.add(banana);
        }
    }

    /* Default Initialization of Entities
    1) clear all lists. 2) Create GameEntities based on level specification 3) Put them into EntityMap.*/
    public void initializeEntities(Levels level) throws CloneNotSupportedException {
        clearEntityLists();
        populateEntities(level);
    }

    // Drawing of Entities
    public void drawEntities(SpriteBatch sb) {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                entity.draw(sb);
                entity.updateEntityHitbox();
            }
        }
    }

    // Respawning of Entities
    public void respawnEntities(Levels sceneLevelAssets) {
        timeSinceLastSpawn += 1;

        if (timeSinceLastSpawn >= 60f) {
            timeSinceLastSpawn = 0; // Reset the timer

            for (String entityType : sceneLevelAssets.getRespawnables()) {
                int currentCount = getCurrentEntityCount(entityType);
                int maxCount = getMaxCountForEntity(entityType, sceneLevelAssets);
                GameEntity entity = null;
                try {
                    entity = randomiseEntityPosition(Objects.requireNonNull(createEntity(getEntityType(entityType), sceneLevelAssets)).clone(), entityMap);
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

                // If not endless, add entities without checking count
                if (sceneLevelAssets.getLevelNumber() != 4) {
                    if (entity instanceof AI) {
                        aiEntityList.add(entity);
                    } else {
                        propEntityList.add(entity);
                    }
                    continue;
                }

                // For level 4, check counts before adding
                if (currentCount < maxCount) {
                    if (entity instanceof AI) {
                        aiEntityList.add(entity);
                    } else {
                        propEntityList.add(entity);
                    }
                }
            }
        }
    }

    // Get Current entity count on screen
    private int getCurrentEntityCount(String entityType) {
        int count = 0;
        // Combine both lists to simplify iteration
        List<GameEntity> allEntities = new ArrayList<>();
        allEntities.addAll(aiEntityList);
        allEntities.addAll(propEntityList);

        for (GameEntity entity : allEntities) {
            if (entity.getEntityType().equals(entityType)) {
                count++;
            }
        }

        return count;
    }

    // Get Max Count for Entity
    private int getMaxCountForEntity(String entityType, Levels levelAssets) {
        switch (entityType) {
            case "FRENCHFRIES":
                return levelAssets.getNumberOfFries();
            case "ROCK":
                return levelAssets.getNumberOfRocks();
            case "APPLE":
                return levelAssets.getNumberOfApples();
            case "BANANA":
                return levelAssets.getNumberOfBananas();
            case "BURGER":
                return levelAssets.getNumberOfBurgers();
            case "CHERRY":
                return levelAssets.getNumberOfCherries();
            default:
                return 0;
        }
    }


    // Checking player entity status
    public void checkPlayerEntityStatus(int scoreNeeded) {
        for (GameEntity entity: playerEntityList) {
            Player player = (Player) entity;
            player.checkWinCondition(scoreNeeded);
            player.checkLoseCondition();
        }
    }

    // Randomise Entity position
    private GameEntity randomiseEntityPosition(GameEntity entity, Map<String, List<GameEntity>> entityMap) {
        final float MIN_DISTANCE = 100f;
        boolean positionValid;
        GameEntity player = entityMap.get("player").get(0);

        Rectangle spawnArea = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        do {
            // Generate a random position within the screen bounds
            float randomX = MathUtils.random(spawnArea.x, spawnArea.width - entity.getWidth());
            float randomY = MathUtils.random(spawnArea.y, spawnArea.height - entity.getHeight());
            Vector2 randomPosition = new Vector2(randomX, randomY);

            positionValid = true;

            // Check distance from the player
            if (randomPosition.dst(player.getPosX(), player.getPosY()) < MIN_DISTANCE) {
                positionValid = false;
            }

//          Check distance from AI entities
            for (GameEntity aiEntity : entityMap.getOrDefault("ai", Collections.emptyList())) {
                if (new Vector2(aiEntity.getPosX(), aiEntity.getPosY()).dst(randomPosition) < MIN_DISTANCE) {
                    positionValid = false;
                    break;
                }
            }

            // Check distance from prop entities
            for (GameEntity prop : entityMap.getOrDefault("props", Collections.emptyList())) {
                if (new Vector2(prop.getPosX(), prop.getPosY()).dst(randomPosition) < MIN_DISTANCE) {
                    positionValid = false;
                    break;
                }
            }

            // If the position is valid, set the AI's position
            if (positionValid) {
                entity.setPosX(randomX);
                entity.setPosY(randomY);
            }

        } while (!positionValid);

        return entity;
    }

    // Initialising of entity actions
    public void initialiseEntityActions(Inputs commandInput, EffectManager effectManager) {
        for (GameEntity entity: playerEntityList) {
            Player player = (Player) entity;
            player.playerActions(commandInput, entityMap, effectManager);
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

    // Get time since last spawn
    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    // Set time since last spawn
    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }

}