package com.mygdx.game.EngineLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.EngineLayer.Levels.Levels;

public class Chicken extends AI {
    protected Chicken() {
        super();
    }

    protected Chicken(Levels level) {
        super(level);
        setTexture(new Texture(Gdx.files.internal(level.getEnemyTexture().get(2))));
        setWidth(64);
        setHeight(64);
        setSpeed(getSpeed() + 20);
    }

    protected Chicken(float x, float y) {
        super();
        setPosX(x);
        setPosY(y);
        setWidth(64);
        setHeight(64);
        setSpeed(getSpeed() + 20);
        setTexture(new Texture(Gdx.files.internal("Entities/chicken.png")));
    }

}
