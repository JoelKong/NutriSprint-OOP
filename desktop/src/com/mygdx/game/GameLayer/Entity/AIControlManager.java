package com.mygdx.game.GameLayer.Entity;
import com.mygdx.game.EngineLayer.EngineEntity.EngineAIControlManager;

import java.util.ArrayList;
import java.util.List;

// AI Control Manager Class
public class AIControlManager extends EngineAIControlManager {
    public AIControlManager() {}

    // Initialise all AI behavior
    public void initializeAIBehavior(List<GameEntity> AIList, GameEntity player) {
        List<GameEntity> newEntities = new ArrayList<>();
        for (GameEntity entity : AIList) {
            AI ai = (AI) entity;
            if (ai instanceof Burger) {
                newEntities.addAll(ai.behavior());
            } else {
                ai.behavior(player);
            }
        }
        AIList.addAll(newEntities);
    }

}
