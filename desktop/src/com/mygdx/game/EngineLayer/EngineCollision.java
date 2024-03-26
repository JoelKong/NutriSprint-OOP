package com.mygdx.game.EngineLayer;

public class EngineCollision {
    protected EngineCollision() {}

    // Detects collisions between 2 Entities
    protected boolean collisionDetected(EngineGameEntity A, EngineGameEntity B) {
        return A.getHitbox().overlaps(B.getHitbox());
    }

    // Collision logic for Players and AI
    protected void handleAICollision() {}
    protected void handlePlayerCollisions() {}
}
