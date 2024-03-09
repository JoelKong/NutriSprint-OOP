package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.Levels.Levels;

// Isaac Player Class
public class Isaac extends Player {
    // Declare attributes of Isaac
    private TextureRegion headRegion;
    private TextureRegion bodyRegion;

    // Default Constructor
    protected Isaac() {
        super();
        this.headRegion = new TextureRegion(this.getTexture(), 10, 20, this.getWidth(), this.getHeight());
        this.bodyRegion = new TextureRegion(this.getTexture(), 10, 70, this.getWidth(), this.getHeight());
    }

    protected Isaac(Levels level) {
        super(level);
        this.headRegion = new TextureRegion(this.getTexture(), 10, 20, this.getWidth(), this.getHeight());
        this.bodyRegion = new TextureRegion(this.getTexture(), 10, 70, this.getWidth(), this.getHeight());
    }

    @Override
    // Draw Isaac
    protected void draw(SpriteBatch sb) {
        sb.draw(headRegion, getPosX(), getPosY() + 17);
        sb.draw(bodyRegion, getPosX(), getPosY());
    }

    @Override
//     Movement of Isaac
    protected void playerMovement(Inputs preferredInput) {
        if (preferredInput.getUpKey()) {
            getPlayerControlManager().manageControls("UP", this);
            headRegion = new TextureRegion(getTexture(), 170, 20, getWidth(), getHeight());
        }
        if (preferredInput.getDownKey()) {
            getPlayerControlManager().manageControls("DOWN", this);
            headRegion = new TextureRegion(getTexture(), 10, 20, getWidth(), getHeight());
        }
        if (preferredInput.getLeftKey()) {
            getPlayerControlManager().manageControls("LEFT", this);
            headRegion = new TextureRegion(getTexture(), 250, 20, getWidth(), getHeight());
        }
        if (preferredInput.getRightKey()) {
            getPlayerControlManager().manageControls("RIGHT", this);
            headRegion = new TextureRegion(getTexture(), 90, 20, getWidth(), getHeight());
        }
    }
}
