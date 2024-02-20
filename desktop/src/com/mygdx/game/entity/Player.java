package com.mygdx.game.entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.io.Inputs;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Attributes
    private int playerID;

    // Default Constructor
    protected Player() {
        super();
        this.playerID = 1;
    }

    // Parameterized Constructor to specify player attributes
    protected Player(Texture texture, float xPosition, float yPosition, float speed) {
        super(texture, xPosition, yPosition, speed);
        this.playerID = 1;
    }

    @Override
    // Draw Player
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    // Movement of Player
    protected void playerMovement(Inputs preferredInput, PlayerControls playerControl) {
        if (preferredInput.getUpKey()) {
            playerControl.moveUp(this);
        }
        if (preferredInput.getDownKey()) {
            playerControl.moveDown(this);
        }
        if (preferredInput.getLeftKey()) {
            playerControl.moveLeft(this);
        }
        if (preferredInput.getRightKey()) {
            playerControl.moveRight(this);
        }
    }

    // Get Player ID
    public int getPlayerID() {
        return playerID;
    }

    // Set Player ID
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
}
