package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Apple class
public class Apple extends GameEntity {
    // Default Constructor
    protected Apple() {
        super(new Texture("Entities/apple.png"));
    }

    // Drawing of apple
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}
