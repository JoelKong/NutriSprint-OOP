package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.Levels.Levels;

// NPC Class inherited from GameEntity
public class AI extends GameEntity {
    // Declare Attributes
    private int AIID;

    // Default Constructor defaulting to AI
    protected AI() {
        super();
        setTexture(new Texture("Entities/fries.png"));
        this.AIID = 1;
        this.setSpeed(200);
    }

    protected AI(Levels level) {
        super(level);
        this.AIID = 1;
        this.setSpeed(level.getEnemySpeed());

    }

    @Override
    // Draw AI
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    };

    // AI behavior
    protected void behavior(GameEntity targetPlayer) {}

    // Get AI ID
    public int getAIID() {
        return AIID;
    }

    // Set AI ID
    public void setAIID(int AIID) {
        this.AIID = AIID;
    }
}
