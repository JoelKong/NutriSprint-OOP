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
//     Action of Isaac
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
}
