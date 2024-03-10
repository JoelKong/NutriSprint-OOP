package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.EngineLayer.Levels.Levels;

import java.util.ArrayList;
import java.util.List;

public class Burger extends AI {
    private long lastShotTime;

    protected Burger() {
        super();
        this.lastShotTime = 0;
        setWidth(64);
        setHeight(64);
        setEntityType("BURGER");
    }

    protected Burger(Levels level) {
        super(level);
        setTexture(new Texture(Gdx.files.internal(level.getEnemyTexture().get(1))));
        setWidth(64);
        setHeight(64);
        setEntityType("BURGER");
    }

    // Behavior of burger to shoot chicken at player direction
//    @Override
    protected List<GameEntity> behavior() {
        List<GameEntity> newEntities = new ArrayList<>();
        final long SHOOT_INTERVAL = 5000;
        long timeNow = TimeUtils.millis();

        if (timeNow - lastShotTime > SHOOT_INTERVAL) {
            Chicken chicken = new Chicken(getPosX(), getPosY());
            newEntities.add(chicken);
            lastShotTime = timeNow;
        }
        return newEntities;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }
}
