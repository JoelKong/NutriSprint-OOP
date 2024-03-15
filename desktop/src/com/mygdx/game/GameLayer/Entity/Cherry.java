package com.mygdx.game.GameLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Levels.Levels;

public class Cherry extends GameEntity {
    protected Cherry() {
        super();
        setTexture(new Texture("Entities/cherry.png"));
        setEntityType("CHERRY");
    }

    protected Cherry(Levels level) {
        super();
        setTexture(new Texture(Gdx.files.internal(level.getPropTexture().get(3))));
        setEntityType("CHERRY");
    }

    // Drawing of apple
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}
