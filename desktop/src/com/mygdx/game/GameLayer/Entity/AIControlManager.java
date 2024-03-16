package com.mygdx.game.GameLayer.Entity;
import java.util.ArrayList;
import java.util.List;

// AI Control Manager Class
public class AIControlManager {
    public AIControlManager() {}

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
