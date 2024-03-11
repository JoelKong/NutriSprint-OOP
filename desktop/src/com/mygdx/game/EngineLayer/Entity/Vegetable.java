package com.mygdx.game.EngineLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.Levels.Levels;

public class Vegetable extends GameEntity {
    // Default Constructor
    protected Vegetable() {
        super();
        setTexture(new Texture("Entities/vegetable.png"));
        setEntityType("VEGETABLE");
    }

    protected Vegetable(Levels level) {
        super();
        setEntityType("VEGETABLE");
        setTexture(new Texture(Gdx.files.internal(level.getPropTexture().get(4))));
    }

    // Drawing of apple
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}
