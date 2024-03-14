package com.mygdx.game.EngineLayer.Entity;
import com.mygdx.game.EngineLayer.Sound.SoundManager;

import java.util.ArrayList;
import java.util.List;

// AI Control Manager Class
public class AIControlManager {
    // Default Constructor
    public AIControlManager() {}

    // Initialise AI Behavior
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
