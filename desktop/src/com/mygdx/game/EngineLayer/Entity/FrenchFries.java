package com.mygdx.game.EngineLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

// FrenchFries Enemy class
public class FrenchFries extends AI {
    // Default Constructor
    protected FrenchFries() {
        super();
    }

    // Behavior of fries to chase the player
    @Override
    protected void behavior(GameEntity targetPlayer) {
        // Calculate the direction vector from the AI to the player
        Vector2 aiPosition = new Vector2(this.getPosX(), this.getPosY());
        Vector2 playerPosition = new Vector2(targetPlayer.getPosX(), targetPlayer.getPosY());
        Vector2 direction = playerPosition.sub(aiPosition).nor();

        // Calculate the new position of the AI
        Vector2 newPosition = aiPosition.add(direction.scl(getSpeed() * Gdx.graphics.getDeltaTime()));

        // Update the AI's position
        this.setPosX(newPosition.x);
        this.setPosY(newPosition.y);

        // Update the AI's hitbox position to the new position
        this.getHitbox().setPosition(newPosition.x, newPosition.y);
    }
}
