package com.mygdx.game.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Controls.PlayerControls;
import com.mygdx.game.InputOutput.KeyboardMouse;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Variables
    private int playerID;

    // Default Constructor
    protected Player() {
        super();
        this.playerID = 1;
    }

    // Parameterized Constructor
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
    protected void movement(KeyboardMouse keys, PlayerControls playerInput) {
        if (keys.getUpKey()) {
            playerInput.moveUp(this);
        }
        if (keys.getDownKey()) {
            playerInput.moveDown(this);
        }
        if (keys.getLeftKey()) {
            playerInput.moveLeft(this);
        }
        if (keys.getRightKey()) {
            playerInput.moveRight(this);
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
