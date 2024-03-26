package com.mygdx.game.EngineLayer.EngineCollision;

import com.mygdx.game.EngineLayer.EngineCollision.EngineCollision;

public abstract class EngineCollisionManager {
    private EngineCollision engineCollision;

    public EngineCollisionManager() {
        this.engineCollision = new EngineCollision();
    }
    public void initializeCollisions() {

    }
}
