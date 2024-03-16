package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Levels.Levels;
import com.mygdx.game.GameLayer.Sound.SoundManager;

import java.util.List;
import java.util.Map;

// Isaac Player Class
public class Isaac extends Player {
    // Declare attributes of Isaac
    private TextureRegion headRegion;
    private TextureRegion bodyRegion;
    private int teleportDistance;
    private int teleportCooldown;
    private int lastTeleportTime;
    private int explodeMeter;

    // Default Constructor
    protected Isaac() {
        super();
        this.headRegion = new TextureRegion(this.getTexture(), 10, 20, this.getWidth(), this.getHeight());
        this.bodyRegion = new TextureRegion(this.getTexture(), 10, 70, this.getWidth(), this.getHeight());
        this.teleportDistance = 200;
        this.teleportCooldown = 5000;
        this.lastTeleportTime = 0;
        this.explodeMeter = 0;
    }

    protected Isaac(Levels level) {
        super(level);
        this.headRegion = new TextureRegion(this.getTexture(), 10, 20, this.getWidth(), this.getHeight());
        this.bodyRegion = new TextureRegion(this.getTexture(), 10, 70, this.getWidth(), this.getHeight());
        this.teleportDistance = 200;
        this.teleportCooldown = 5000;
        this.lastTeleportTime = 0;
        this.explodeMeter = 0;
    }

    @Override
    // Draw Isaac
    protected void draw(SpriteBatch sb) {
        sb.draw(headRegion, getPosX(), getPosY() + 17);
        sb.draw(bodyRegion, getPosX(), getPosY());
    }

    // Action of Isaac
    protected void playerActions(Inputs preferredInput, Map<String, List<GameEntity>> entityMap, EffectManager effectManager, SoundManager soundManager) {
        if (preferredInput.getUpKey()) {
            getPlayerControlManager().manageControls("UP", this, preferredInput, entityMap, effectManager, soundManager);
            headRegion = new TextureRegion(getTexture(), 170, 20, getWidth(), getHeight());
        }
        if (preferredInput.getDownKey()) {
            getPlayerControlManager().manageControls("DOWN", this, preferredInput, entityMap, effectManager, soundManager);
            headRegion = new TextureRegion(getTexture(), 10, 20, getWidth(), getHeight());
        }
        if (preferredInput.getLeftKey()) {
            getPlayerControlManager().manageControls("LEFT", this, preferredInput, entityMap, effectManager, soundManager);
            headRegion = new TextureRegion(getTexture(), 250, 20, getWidth(), getHeight());
        }
        if (preferredInput.getRightKey()) {
            getPlayerControlManager().manageControls("RIGHT", this, preferredInput, entityMap, effectManager, soundManager);
            headRegion = new TextureRegion(getTexture(), 90, 20, getWidth(), getHeight());
        }
        if (preferredInput.getTeleportKey()) {
            getPlayerControlManager().manageControls("TELEPORT", this, preferredInput, entityMap, effectManager, soundManager);
        }
        if (preferredInput.getExplodeKey()) {
            getPlayerControlManager().manageControls("EXPLODE", this, preferredInput, entityMap, effectManager, soundManager);
        }
    }

    // Getters and Setters
    public TextureRegion getHeadRegion() {
        return headRegion;
    }

    public void setHeadRegion(TextureRegion headRegion) {
        this.headRegion = headRegion;
    }

    public TextureRegion getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(TextureRegion bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public int getTeleportDistance() {
        return teleportDistance;
    }

    public void setTeleportDistance(int teleportDistance) {
        this.teleportDistance = teleportDistance;
    }

    public int getTeleportCooldown() {
        return teleportCooldown;
    }

    public void setTeleportCooldown(int teleportCooldown) {
        this.teleportCooldown = teleportCooldown;
    }

    public int getLastTeleportTime() {
        return lastTeleportTime;
    }

    public void setLastTeleportTime(int lastTeleportTime) {
        this.lastTeleportTime = lastTeleportTime;
    }

    public int getExplodeMeter() {
        return explodeMeter;
    }

    public void setExplodeMeter(int explodeMeter) {
        this.explodeMeter = explodeMeter;
    }
}
