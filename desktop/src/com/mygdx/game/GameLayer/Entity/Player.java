package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.PlayerControls.PlayerControlManager;
import com.mygdx.game.GameLayer.Sound.SoundManager;

import java.util.List;
import java.util.Map;

// Player class inherited from GameEntity
public class Player extends GameEntity {
    // Declare Attributes
    private int health;
    private int score;
    private boolean winStatus;
    private boolean loseStatus;
    private PlayerControlManager playerControlManager;

    // Default Constructor
    protected Player() {
        super();
        this.health = 10;
        this.score = 0;
        this.winStatus = false;
        this.loseStatus = false;
        this.playerControlManager = new PlayerControlManager();
    }

    // Parameterized Constructor to specify player attributes
    protected Player(Levels level) {
        super(level);
        this.health = 10;
        this.score = 0;
        this.winStatus = false;
        this.loseStatus = false;
        this.playerControlManager = new PlayerControlManager();
    }

    @Override
    // Draw Player
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }

    // Reset Player
    protected void resetPlayer() {};

    // Actions of Player
    protected void playerActions(Inputs preferredInput, Map<String, List<GameEntity>> entityMap, EffectManager effectManager, SoundManager soundManager) {};

    // Player Win Condition (if not endless mode and score meets requirement then player wins level)
    protected void checkWinCondition(Levels sceneLevelAssets) {
        if (sceneLevelAssets.getLevelNumber() != 4) {
            int scoreNeeded = Integer.parseInt(sceneLevelAssets.getScoreNeeded());
            if (score == scoreNeeded) {
                winStatus = true;
            }
        }
    }

    // Player Lose Condition (if health 0 then die)
    protected void checkLoseCondition(SoundManager soundManager) {
        if (health == 0) {
            soundManager.playSoundEffect("PLAYERDEATH");
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

    // Get Player Control Manager
    public PlayerControlManager getPlayerControlManager() {
        return playerControlManager;
    }
}
