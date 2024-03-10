package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.Levels.Levels;

// Apple class
public class Apple extends GameEntity {
    // Default Constructor
    protected Apple() {
        super();
        setTexture(new Texture("Entities/apple.png"));
        setEntityType("APPLE");
    }

    protected Apple(Levels level) {
        super();
        setEntityType("APPLE");
        setTexture(new Texture(Gdx.files.internal(level.getPropTexture().get(1))));
    }

    // Drawing of apple
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}
