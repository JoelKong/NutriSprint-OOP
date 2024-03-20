package com.mygdx.game.GameLayer.Entity;

public interface EntityAttributeListener {
    void onTeleportCooldownChange(int newTeleportCooldown);
    void onScoreChange(int newScore);
    void onHealthChange(int newHealth);
}
