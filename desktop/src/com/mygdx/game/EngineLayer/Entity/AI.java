package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EngineLayer.Entity.GameEntity;
import com.mygdx.game.EngineLayer.Entity.Player;

// NPC Class inherited from GameEntity
public class AI extends GameEntity {
    // Declare Attributes
    private int AIID;

    // Default Constructor defaulting to AI
    protected AI() { // rmb make protected once got new people
        super(new Texture("Entities/fries.png"));
        this.AIID = 1;
        this.setSpeed(250);
    }

    // Parameterized constructor with customised NPC
    public AI(Texture texture, int npcID) {
        super(texture);
        this.AIID = npcID;
        this.setSpeed(250);
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
