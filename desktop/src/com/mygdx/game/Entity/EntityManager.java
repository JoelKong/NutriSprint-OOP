package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Collisions.Collision;
import com.mygdx.game.Controls.PlayerControls;
import com.mygdx.game.InputOutput.Inputs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
    }

    // Initialization of Entities
    public void initializeEntities() {
        entityMap.clear();
        playersList.clear();
        spawnablesList.clear();
        spawnableHitboxList.clear();
        playersList.add(new Player());
        entityMap.put("player", playersList);
        entityMap.put("spawnables", spawnablesList);
        try {
            randomSpawnablesPosition(7, new AI());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // Drawing of Entities
    public void drawEntities(SpriteBatch sb) {
        for (List<GameEntity> entities: entityMap.values()) {
            for (GameEntity entity: entities) {
                if (!entity.getPopFromScreen()) {
                    entity.draw(sb);
                    Rectangle hitbox = entity.getHitbox();
                    hitbox.setX(entity.getPosX());
                    hitbox.setY(entity.getPosY());
                    entity.setHitbox(hitbox);
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

    // Moving of player
    public void initializePlayerMovement(Inputs commandInput, PlayerControls playerControls) {
        for (GameEntity entity: entityMap.get("player")) {
            Player player = (Player) entity;
            player.movement(commandInput, playerControls);
        }
    }

    // Initialize spawnables in random position, checking if spawn clashes with any other entity
    public void randomSpawnablesPosition(int numberOfEntities, GameEntity clonePrototype) throws CloneNotSupportedException {
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

    // Get Entity Map
    public Map<String, List<GameEntity>> getEntityMap() {
        return entityMap;
    }

    // Set Entity Map
    public void setEntityMap(Map<String, List<GameEntity>> entityMap) {
        this.entityMap = entityMap;
    }

    // Get Spawnable Hitbox List
    public List<Rectangle> getSpawnableHitboxList() {
        return spawnableHitboxList;
    }

    // Set Spawnable Hitbox List
    public void setSpawnableHitboxList(List<Rectangle> spawnableHitboxList) {
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