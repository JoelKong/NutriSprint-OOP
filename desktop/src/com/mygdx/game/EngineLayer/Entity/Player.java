package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.PlayerControls.PlayerControlManager;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Attributes
    private int playerID;
    private PlayerControlManager playerControlManager;
    private int health;

    // Default Constructor
    protected Player() {
        super();
        this.playerID = 1;
        this.health = 5;
        this.playerControlManager = new PlayerControlManager();
    }

    // Parameterized Constructor to specify player attributes
    protected Player(Texture texture, float xPosition, float yPosition, float speed) {
        super(texture, xPosition, yPosition, speed);
        this.playerID = 1;
        this.playerControlManager = new PlayerControlManager();
    }

    @Override
    // Draw Player
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    // Movement of Player
    protected void playerMovement(Inputs preferredInput) {
        if (preferredInput.getUpKey()) {
            playerControlManager.manageControls("UP", this);
        }
        if (preferredInput.getDownKey()) {
            playerControlManager.manageControls("DOWN", this);
        }
        if (preferredInput.getLeftKey()) {
            playerControlManager.manageControls("LEFT", this);
        }
        if (preferredInput.getRightKey()) {
            playerControlManager.manageControls("RIGHT", this);
        }
    }

    // Get Player Health
    public int getHealth() {
        return health;
    }

    // Set Player Health
    public void setHealth(int health) {
        this.health = health;
    }

    // Get Player ID
    public int getPlayerID() {
        return playerID;
    }

    // Set Player ID
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    // Get Player Control Manager
    public PlayerControlManager getPlayerControlManager() {
        return playerControlManager;
    }
}
