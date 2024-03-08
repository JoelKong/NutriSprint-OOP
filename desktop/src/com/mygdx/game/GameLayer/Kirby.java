package com.mygdx.game.GameLayer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;

// Kirby Player Class
public class Kirby extends Player {
    // Declare attributes of Kirby
    private TextureRegion kirbyTexture;

    // Default Constructor
    public Kirby() {
        super();
        this.kirbyTexture = new TextureRegion(this.getTexture(), 0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    // Draw Isaac
    protected void draw(SpriteBatch sb) {
        sb.draw(kirbyTexture, getPosX(), getPosY() + 17);
    }

    @Override
//     Movement of Isaac
    protected void playerMovement(Inputs preferredInput) {
        if (preferredInput.getUpKey()) {
            getPlayerControlManager().manageControls("UP", this);
        }
        if (preferredInput.getDownKey()) {
            getPlayerControlManager().manageControls("DOWN", this);
        }
        if (preferredInput.getLeftKey()) {
            getPlayerControlManager().manageControls("LEFT", this);
            if (!kirbyTexture.isFlipX()) {
                kirbyTexture.flip(true, false);
            }
        }
        if (preferredInput.getRightKey()) {
            getPlayerControlManager().manageControls("RIGHT", this);
            if (kirbyTexture.isFlipX()) {
                kirbyTexture.flip(true, false);
            }
        }
    }
}
