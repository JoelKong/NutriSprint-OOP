package com.mygdx.game.AI;
import com.mygdx.game.Entity.AI;
import com.mygdx.game.Entity.GameEntity;
import java.util.List;
import java.util.Map;

public class AIControlManager {

    public AIControlManager() {}

    public void enableAIBehavior (Map<String, List<GameEntity>> entityMap) {
        for (GameEntity entity: entityMap.get("spawnables")) {
            AI ai = (AI) entity;
            ai.behavior();
        }
    }
}
