package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Levels.Levels;

public class Banana extends GameEntity {
    protected Banana() {
        super();
        setTexture(new Texture("Entities/banana.png"));
        setEntityType("BANANA");
    }

    protected Banana(Levels level) {
        super();
        setTexture(new Texture(Gdx.files.internal(level.getPropTexture().get(2))));
        setEntityType("BANANA");
    }

    // Drawing of banana
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}
