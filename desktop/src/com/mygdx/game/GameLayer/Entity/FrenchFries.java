package com.mygdx.game.GameLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameLayer.Levels.Levels;

// FrenchFries Enemy class
public class FrenchFries extends AI {
    protected FrenchFries() {
        super();
        setEntityType("FRENCHFRIES");
        setTexture(new Texture("Entities/fries.png"));
    }

    protected FrenchFries(Levels level) {
        super(level);
        setEntityType("FRENCHFRIES");
        setTexture(new Texture(Gdx.files.internal(level.getEnemyTexture().get(0))));
    }
}
