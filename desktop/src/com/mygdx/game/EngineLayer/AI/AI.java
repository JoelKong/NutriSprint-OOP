package com.mygdx.game.EngineLayer.AI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.Entity.GameEntity;

// NPC Class inherited from GameEntity
public class AI extends GameEntity {
    // Declare Attributes
    private int AIID;

    // Default Constructor defaulting to AI
    public AI() { // rmb make protected once got new people
        super(new Texture("droplet.png"));
        this.AIID = 1;
        this.setSpeed(2);
    }

    // Parameterized constructor with customised NPC
    public AI(Texture texture, int npcID) {
        super(texture);
        this.AIID = npcID;
        this.setSpeed(2);
    }

    @Override
    // Draw AI
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    };

    // AI behavior
    protected void behavior() {
        this.setPosY(this.getPosY() - this.getSpeed());
        if (this.getPosY() < 0) {
            this.setPosY(Gdx.graphics.getHeight());
            this.setSpeed(this.getSpeed() + 2);
        }
        if (this.getSpeed() > 10) {
            this.setSpeed(2);
        }
    }

    // Get AI ID
    public int getAIID() {
        return AIID;
    }

    // Set AI ID
    public void setAIID(int AIID) {
        this.AIID = AIID;
    }
}
