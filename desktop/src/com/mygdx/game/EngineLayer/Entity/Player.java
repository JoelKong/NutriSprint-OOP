package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.Levels.Levels;
import com.mygdx.game.EngineLayer.PlayerControls.PlayerControlManager;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Attributes
    private int playerID;
    private int health;
    private int score;
    private boolean winStatus;
    private boolean loseStatus;
    private PlayerControlManager playerControlManager;

    // Default Constructor
    protected Player() {
        super();
        this.playerID = 1;
        this.health = 5;
        this.score = 0;
        this.winStatus = false;
        this.loseStatus = false;
        this.playerControlManager = new PlayerControlManager();
    }

    // Parameterized Constructor to specify player attributes
    protected Player(Levels level) {
        super(level);
        this.playerID = 1;
        this.health = 5;
        this.score = 0;
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

    // Player Win Condition
    protected void checkWinCondition(int scoreNeeded) {
        if (score == scoreNeeded) {
            winStatus = true;
        }
    }

    // Player Lose Condition
    protected void checkLoseCondition() {
        if (health == 0) {
            loseStatus = true;
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

    // Get Player Score
    public int getScore() {
        return score;
    }

    // Set Player Score
    public void setScore(int score) {
        this.score = score;
    }

    // Get Win Status
    public boolean getWinStatus() {
        return winStatus;
    }

    // Set Win Status
    public void setWinStatus(boolean winStatus) {
        this.winStatus = winStatus;
    }

    // Get Lose Status
    public boolean getLoseStatus() {
        return loseStatus;
    }

    // Set Lose Status
    public void setLoseStatus(boolean loseStatus) {
        this.loseStatus = loseStatus;
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
