package com.mygdx.game.Entity;
import java.util.List;
import java.util.Map;

// AI Control Manager Class
public class AIControlManager {
    // Default Constructor
    public AIControlManager() {}

    // Initialise AI Behavior
    public void initializeAIBehavior (Map<String, List<GameEntity>> entityMap) {
        for (GameEntity entity: entityMap.get("spawnables")) {
            AI ai = (AI) entity;
            ai.behavior();
        }
    }
}
