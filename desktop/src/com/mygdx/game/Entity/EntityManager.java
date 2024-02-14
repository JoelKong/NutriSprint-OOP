package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Collisions.Collide;
import com.mygdx.game.Controls.PlayerControls;
import com.mygdx.game.InputOutput.Inputs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Entity Manager Class
public class EntityManager {
    // Declare Maps and Lists of entities
    private Map<String, List<GameEntity>> entityMap;
    private List<Rectangle> spawnableHitboxList;
    private List<GameEntity> spawnablesList;
    private List<GameEntity> playersList;

    // Default Constructor class to initialise entities
    public EntityManager() {
        this.entityMap = new HashMap<>();
        this.spawnableHitboxList = new ArrayList<>();
        this.playersList = new ArrayList<>();
        this.spawnablesList = new ArrayList<>();

        playersList.add(new Player());
        entityMap.put("player", playersList);
        entityMap.put("spawnables", spawnablesList);
        try {
            initializeSpawnables(10, new AI());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // Drawing of Entities
    public void drawEntities(SpriteBatch sb) {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                entity.draw(sb);
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

    // Moving of player
    public void movePlayerEntity(Inputs commandInput, PlayerControls playerControls) {
        for (GameEntity entity: entityMap.get("player")) {
            Player player = (Player) entity;
            player.movement(commandInput, playerControls);
        }
    }

    // Moving of AI entities
    public void moveAIEntity() {
        for (GameEntity entity: entityMap.get("ai")) {
            AI ai = (AI) entity;
            ai.behavior();
        }
    }

    // Initialize spawnables in random position, checking if spawn clashes with any other entity
    public void initializeSpawnables(int numberOfEntities, GameEntity clonePrototype) throws CloneNotSupportedException {
        List<GameEntity> spawnables = entityMap.get("spawnables");

        for (int i = 0; i < numberOfEntities;) {
            GameEntity spawnable = clonePrototype.clone();

            Rectangle extendedHitbox = new Rectangle(
                    spawnable.getHitbox().x - 20,
                    spawnable.getHitbox().y - 20,
                    spawnable.getHitbox().width + 44,
                    spawnable.getHitbox().height + 44
            );
            GameEntity player = entityMap.get("player").get(0);

            boolean clash = extendedHitbox.overlaps(player.getHitbox()); // Check if clash with player
            for (Rectangle existingSpawnable : spawnableHitboxList) { // Check if clash with other AI
                if (extendedHitbox.overlaps(existingSpawnable)) {
                    clash = true;
                    break;
                }
            }

            if (!clash) {
                spawnableHitboxList.add(spawnable.getHitbox());
                spawnables.add(spawnable);
                i++;
            }
        }

        entityMap.put("spawnables", spawnables);
    }

    // Colliding of AI with player (put this function in collision portion)
    public void handleCollidePlayerAI(Player entity, AI ai, Collide collide) {}

    // Get Entity Map
    public Map<String, List<GameEntity>> getEntityMap() {
        return entityMap;
    }

    // Set Entity Map
    public void setEntityMap(Map<String, List<GameEntity>> entityMap) {
        this.entityMap = entityMap;
    }

    // Get Spawnable Hitbox List
    public List<com.badlogic.gdx.math.Rectangle> getSpawnableHitboxList() {
        return spawnableHitboxList;
    }

    // Set Spawnable Hitbox List
    public void setSpawnableHitboxList(List<com.badlogic.gdx.math.Rectangle> spawnableHitboxList) {
        this.spawnableHitboxList = spawnableHitboxList;
    }

    // Get Player List
    public List<GameEntity> getPlayersList() {
        return playersList;
    }

    // Set Player List
    public void setPlayersList(List<GameEntity> playersList) {
        this.playersList = playersList;
    }

    // Get Spawnables List
    public List<GameEntity> getSpawnablesList() {
        return spawnablesList;
    }

    // Set Spawnables List
    public void setSpawnablesList(List<GameEntity> spawnablesList) {
        this.spawnablesList = spawnablesList;
    }
}