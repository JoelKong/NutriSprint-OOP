package com.mygdx.game.GameLayer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EngineLayer.Entity.Player;
import com.mygdx.game.EngineLayer.Entity.PlayerControls;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;

// Isaac Player Class
public class Issac extends Player {
    // Declare attributes of Isaac
    private String directionFacing;
    private TextureRegion headRegion;
    private TextureRegion bodyRegion;

    // Default Constructor
    protected Issac() {
        super();
        this.headRegion = new TextureRegion(this.getTexture(), 10, 20, this.getWidth(), this.getHeight());
        this.bodyRegion = new TextureRegion(this.getTexture(), 10, 70, this.getWidth(), this.getHeight());
        this.directionFacing = "down";
    }

    @Override
    // Draw Isaac
    protected void draw(SpriteBatch sb) {
        sb.draw(headRegion, getPosX(), getPosY() + 17);
        sb.draw(bodyRegion, getPosX(), getPosY());
    }

//    @Override
    // Movement of Isaac
//    protected void playerMovement(Inputs preferredInput) {
//        PlayerControls playerControl = getPlayerControlManager().getPlayerControls();
//
//        if (preferredInput.getUpKey()) {
//            playerControl.moveUp(this);
//            headRegion = new TextureRegion(getTexture(), 170, 20, getWidth(), getHeight());
//        }
//        if (preferredInput.getDownKey()) {
//            playerControl.moveDown(this);
//        }
//        if (preferredInput.getLeftKey()) {
//            playerControl.moveLeft(this);
//        }
//        if (preferredInput.getRightKey()) {
//            playerControl.moveRight(this);
//        }
//    }
}
