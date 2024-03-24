package com.mygdx.game.GameLayer.Entity;

// Listen to changes in entity state
public interface EntityAttributeListener {
    void onTeleportCooldownChange(int newTeleportCooldown);
    void onScoreChange(int newScore);
    void onHealthChange(int newHealth);
    void onExplodeMeterChange(int newExplodeMeter);
}
