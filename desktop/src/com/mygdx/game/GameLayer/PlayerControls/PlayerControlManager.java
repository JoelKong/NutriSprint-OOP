package com.mygdx.game.GameLayer.PlayerControls;

import com.mygdx.game.GameLayer.Effects.EffectManager;
import com.mygdx.game.GameLayer.Entity.GameEntity;
import com.mygdx.game.GameLayer.Entity.Player;
import com.mygdx.game.GameLayer.InputOutput.Inputs;
import com.mygdx.game.GameLayer.Sound.SoundManager;

import java.util.List;
import java.util.Map;

// Control Manager Class
public class PlayerControlManager {
    // Declare Attributes
    private PlayerControls playerControls;

    // Default Constructor to initialise player controls
    public PlayerControlManager() {
        this.playerControls = new PlayerControls();
    }


    // Manage Player Controls
    public void manageControls(String command, Player player, Inputs preferredInput, Map<String, List<GameEntity>> entityMap, EffectManager effectManager, SoundManager soundManager) {
        switch (command) {
            case "UP":
                playerControls.moveUp(player);
                break;
            case "DOWN":
                playerControls.moveDown(player);
                break;
            case "LEFT":
                playerControls.moveLeft(player);
                break;
            case "RIGHT":
                playerControls.moveRight(player);
                break;
            case "TELEPORT":
                playerControls.teleport(player, preferredInput, effectManager);
                soundManager.playSoundEffect("TELEPORT");
                break;
            case "EXPLODE":
                if (player.getExplodeMeter() == 3) {
                    soundManager.playSoundEffect("EXPLOSION");
                    playerControls.triggerExplosion(player, entityMap, effectManager);
                    player.setExplodeMeter(0);
                }
                break;
            default:
        }
    }

    // Get Player Controls
    public PlayerControls getPlayerControls() {
        return playerControls;
    }

    // Set Player Controls
    public void setPlayerControls(PlayerControls playerControls) {
        this.playerControls = playerControls;
    }
}
