package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.EngineEntityManager;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.Sound.SoundManager;
import java.util.*;

// Entity Manager Class
public class EntityManager extends EngineEntityManager {
    private List<GameEntity> propEntityList;
    private float timeSinceLastSpawn;
    private EntityFactory entityFactory;

    // Initialise all forms of entity lists as well as its factory
    public EntityManager() {
        super();
        this.propEntityList = new ArrayList<>();
        this.entityFactory = new EntityFactory();
        getEntityMap().put("props", propEntityList);
    }

    // Reset all entities
    @Override
    public void resetEntities() {
        super.resetEntities();
        propEntityList.clear();
    }

    // Populate entities based off level specification
    public void populateEntities(Levels level) throws CloneNotSupportedException {
        getPlayerEntityList().add(entityFactory.createEntity("ISAAC", level));

        for (int i = 0; i < level.getNumberOfFries(); i++) {
            GameEntity entity = randomiseEntityPosition(entityFactory.createEntity("FRENCHFRIES", level).clone(), getEntityMap());
            getAiEntityList().add(entity);
        }

        for (int i = 0; i < level.getNumberOfBurgers(); i++) {
            GameEntity entity = randomiseEntityPosition(entityFactory.createEntity("BURGER", level).clone(), getEntityMap());
            getAiEntityList().add(entity);
        }

        for (int i = 0; i < level.getNumberOfRocks(); i++) {
            GameEntity rock = randomiseEntityPosition(entityFactory.createEntity("ROCK", level).clone(), getEntityMap());
            propEntityList.add(rock);
        }

        for (int i = 0; i < level.getNumberOfApples(); i++) {
            GameEntity apple = randomiseEntityPosition(entityFactory.createEntity("APPLE", level).clone(), getEntityMap());
            propEntityList.add(apple);
        }

        for (int i = 0; i < level.getNumberOfVegetables(); i++) {
            GameEntity vegetable = randomiseEntityPosition(entityFactory.createEntity("VEGETABLE", level).clone(), getEntityMap());
            propEntityList.add(vegetable);
        }

        for (int i = 0; i < level.getNumberOfBananas(); i++) {
            GameEntity banana = randomiseEntityPosition(entityFactory.createEntity("BANANA", level).clone(), getEntityMap());
            propEntityList.add(banana);
        }

        for (int i = 0; i < level.getNumberOfCherries(); i++) {
            GameEntity banana = randomiseEntityPosition(entityFactory.createEntity("CHERRY", level).clone(), getEntityMap());
            propEntityList.add(banana);
        }
    }

    /* Default Initialization of Entities
    1) clear all lists. 2) Create GameEntities based on level specification 3) Put them into EntityMap.*/
    public void initializeEntities(Levels level) throws CloneNotSupportedException {
        resetEntities();
        populateEntities(level);
    }

    // Drawing of Entities
    public void drawEntities(SpriteBatch sb) {
        for (List<GameEntity> entities: getEntityMap().values()) {
            for (GameEntity entity: entities) {
                entity.draw(sb);
                entity.updateEntityHitbox();
            }
        }
    }

    // Respawning of Entities
    public void respawnEntities(Levels sceneLevelAssets) {
        timeSinceLastSpawn += 1;

        if (timeSinceLastSpawn >= 100f) {
            timeSinceLastSpawn = 0; // Reset the timer
            for (String entityType : sceneLevelAssets.getRespawnables()) {
                int currentCount = getCurrentEntityCount(entityType);
                int maxCount = getMaxCountForEntity(entityType, sceneLevelAssets);
                GameEntity entity = null;
                try {
                    entity = randomiseEntityPosition(entityFactory.createEntity(entityType, sceneLevelAssets).clone(), getEntityMap());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }

                // If not endless, add entities without checking count
                if (sceneLevelAssets.getLevelNumber() != 4) {
                    if (entity instanceof AI) {
                        getAiEntityList().add(entity);
                    } else {
                        propEntityList.add(entity);
                    }
                    continue;
                }

                // For level 4, check counts before adding
                if (currentCount < maxCount) {
                    if (entity instanceof AI) {
                        getAiEntityList().add(entity);
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
        allEntities.addAll(getAiEntityList());
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
            case "VEGETABLE":
                return levelAssets.getNumberOfVegetables();
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
    public void checkPlayerEntityStatus(Levels sceneLevelAssets, SoundManager soundManager) {
        for (GameEntity entity: getPlayerEntityList()) {
            Player player = (Player) entity;
            player.checkWinCondition(sceneLevelAssets);
            player.checkLoseCondition(soundManager);
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

            // Check distance from AI entities
            for (GameEntity aiEntity : getAiEntityList()) {
                if (new Vector2(aiEntity.getPosX(), aiEntity.getPosY()).dst(randomPosition) < MIN_DISTANCE) {
                    positionValid = false;
                    break;
                }
            }

            // Check distance from rock entities
            for (GameEntity prop : propEntityList) {
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
    public void initialiseEntityActions(Inputs commandInput, EffectManager effectManager, SoundManager soundManager) {
        for (GameEntity entity: getPlayerEntityList()) {
            Player player = (Player) entity;
            player.playerActions(commandInput, getEntityMap(), effectManager, soundManager);
        }
    }

    // Disposing of Entities
    public void disposeEntities() {
        for (List<GameEntity> entities: getEntityMap().values()) {
            for (GameEntity entity: entities) {
                entity.getTexture().dispose();
            }
        }
    }

    // Get time since last spawn
    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    // Set time since last spawn
    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }

    public List<GameEntity> getPropEntityList() {
        return propEntityList;
    }

    public void setPropEntityList(List<GameEntity> propEntityList) {
        this.propEntityList = propEntityList;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    public void setEntityFactory(EntityFactory entityFactory) {
        this.entityFactory = entityFactory;
    }

}