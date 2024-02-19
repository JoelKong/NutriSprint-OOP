package com.mygdx.game.Entity;
import java.util.List;
import java.util.Map;

// AI Control Manager Class
public class AIControlManager {
    // Default Constructor
    public AIControlManager() {}

    // Initialise AI Behavior
    public void initializeAIBehavior (List<AI> AIList) {
        for (AI ai: AIList) {
            ai.behavior();
        }
    }
}
