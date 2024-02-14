package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// NPC Class inherited from GameEntity
public class AI extends GameEntity{
    // Declare Variables
    private int AIID;

    // Default Constructor defaulting to enemy
    protected AI() {
        super(new Texture("droplet.png"));
        this.AIID = 1;
    }

    // Parameterized constructor with customised NPC
    protected AI(Texture texture, int npcID) {
        super(texture);
        this.AIID = npcID;
    }

    @Override
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    };

    public void behavior() {
        setPosY(getPosY() - getSpeed());
        if (getPosY() <= 0) {
            setPosY(400);
        }
    }


    // Get Npc ID
    public int getNpcID() {
        return AIID;
    }

    // Set Npc ID
    public void setNpcID(int AIID) {
        this.AIID = AIID;
    }
}
