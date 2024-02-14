package com.mygdx.game.Entity;
import java.util.List;
import java.util.Map;

public class AIControlManager {

    public AIControlManager() {}

    public void initializeAIBehavior (Map<String, List<GameEntity>> entityMap) {
        for (GameEntity entity: entityMap.get("spawnables")) {
            AI ai = (AI) entity;
            ai.behavior();
        }
    }
}
