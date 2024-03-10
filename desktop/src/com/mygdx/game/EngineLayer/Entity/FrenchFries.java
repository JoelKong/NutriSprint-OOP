package com.mygdx.game.EngineLayer.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Levels.Levels;

// FrenchFries Enemy class
public class FrenchFries extends AI {
    // Default Constructor
    protected FrenchFries() {
        super();
    }

    protected FrenchFries(Levels level) {
        super(level);
        setTexture(new Texture(Gdx.files.internal(level.getEnemyTexture().get(0))));
    }
}
