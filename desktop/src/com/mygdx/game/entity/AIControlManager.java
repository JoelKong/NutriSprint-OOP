package com.mygdx.game.entity;
import java.util.List;

// AI Control Manager Class
public class AIControlManager {
    // Default Constructor
    public AIControlManager() {}

    // Initialise AI Behavior
    public void initializeAIBehavior (List<GameEntity> AIList) {
        for (GameEntity entity: AIList) {
            AI ai = (AI) entity;
            ai.behavior();
        }
    }
}
