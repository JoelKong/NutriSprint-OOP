package com.mygdx.game.EngineLayer.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.EngineLayer.InputOutput.Inputs;
import com.mygdx.game.EngineLayer.Levels.Levels;
import com.mygdx.game.EngineLayer.PlayerControls.PlayerControlManager;

import java.util.List;
import java.util.Map;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Attributes
    private int playerID;
    private int health;
    private int score;
    private int explodeMeter;
    private boolean winStatus;
    private boolean loseStatus;
    private long lastTeleportTime;
    private float teleportDistance;
    private long teleportCooldown;
    private PlayerControlManager playerControlManager;

    // Default Constructor
    protected Player() {
        super();
        this.playerID = 1;
        this.health = 10;
        this.score = 0;
        this.winStatus = false;
        this.loseStatus = false;
        this.teleportDistance = 100;
        this.teleportCooldown = 5000;
        this.explodeMeter = 0;
        this.playerControlManager = new PlayerControlManager();
    }

    // Parameterized Constructor to specify player attributes
    protected Player(Levels level) {
        super(level);
        this.playerID = 1;
        this.health = 10;
        this.score = 0;
        this.winStatus = false;
        this.loseStatus = false;
        this.teleportDistance = 200;
        this.teleportCooldown = 5000;
        this.explodeMeter = 0;
        this.playerControlManager = new PlayerControlManager();
    }

    @Override
    // Draw Player
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    // Actions of Player
    protected void playerActions(Inputs preferredInput, Map<String,List<GameEntity>> entityMap) {
        if (preferredInput.getUpKey()) {
            playerControlManager.manageControls("UP", this, preferredInput, entityMap);
        }
        if (preferredInput.getDownKey()) {
            playerControlManager.manageControls("DOWN", this, preferredInput, entityMap);
        }
        if (preferredInput.getLeftKey()) {
            playerControlManager.manageControls("LEFT", this, preferredInput, entityMap);
        }
        if (preferredInput.getRightKey()) {
            playerControlManager.manageControls("RIGHT", this, preferredInput, entityMap);
        }
        if (preferredInput.getTeleportKey()) {
            playerControlManager.manageControls("TELEPORT", this, preferredInput, entityMap);
        }
        if (preferredInput.getExplodeKey()) {
            playerControlManager.manageControls("EXPLODE", this, preferredInput, entityMap);
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

    public float getTeleportDistance() {
        return teleportDistance;
    }

    public void setTeleportDistance(float teleportDistance) {
        this.teleportDistance = teleportDistance;
    }

    public long getTeleportCooldown() {
        return teleportCooldown;
    }

    public void setTeleportCooldown(long teleportCooldown) {
        this.teleportCooldown = teleportCooldown;
    }

    public long getLastTeleportTime() {
        return lastTeleportTime;
    }

    public void setLastTeleportTime(long lastTeleportTime) {
        this.lastTeleportTime = lastTeleportTime;
    }

    public int getExplodeMeter() {
        return explodeMeter;
    }

    public void setExplodeMeter(int explodeMeter) {
        this.explodeMeter = explodeMeter;
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
