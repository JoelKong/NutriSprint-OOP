package com.mygdx.game.GameLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameLayer.Levels.Levels;

// Rock class
public class Rock extends GameEntity {
    private TextureRegion rock;

    // Default constructor of rock
    protected Rock() {
        super();
        setEntityType("ROCK");
        setTexture(new Texture("Entities/rock.png"));
        this.rock = new TextureRegion(this.getTexture(), 0, 0, this.getWidth(), this.getHeight());
    }

    protected Rock(Levels level) {
        super(level);
        setEntityType("ROCK");
        setTexture(new Texture(Gdx.files.internal(level.getPropTexture().get(0))));
        this.rock = new TextureRegion(this.getTexture(), 0, 0, this.getWidth(), this.getHeight());
    }

    // Drawing of rock
    protected void draw(SpriteBatch sb) {
        sb.draw(rock, this.getPosX(), this.getPosY());
    }

    // Get Rock
    public TextureRegion getRock() {
        return rock;
    }

    // Set Rock
    public void setRock(TextureRegion rock) {
        this.rock = rock;
    }
}