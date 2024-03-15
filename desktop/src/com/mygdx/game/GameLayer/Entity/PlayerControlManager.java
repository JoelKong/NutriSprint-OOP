package com.mygdx.game.GameLayer.Entity;

import com.mygdx.game.EngineLayer.EnginePlayerControlManager;

// Control Manager Class
public class PlayerControlManager extends EnginePlayerControlManager {
    // Declare Attributes
    private PlayerControls playerControls;

    // Default Constructor to initialise player controls
    public PlayerControlManager() {
        this.playerControls = new PlayerControls();
    }
    @Override
    public void manageControls() {
    }

    // Manage Player Controls
//    public void manageControls(String command, Player player, Inputs preferredInput, Map<String, List<GameEntity>> entityMap, EffectManager effectManager, SoundManager soundManager) {
//        switch (command) {
//            case "UP":
//                playerControls.moveUp(player);
//                break;
//            case "DOWN":
//                playerControls.moveDown(player);
//                break;
//            case "LEFT":
//                playerControls.moveLeft(player);
//                break;
//            case "RIGHT":
//                playerControls.moveRight(player);
//                break;
//            case "TELEPORT":
//                playerControls.teleport(player, preferredInput, effectManager);
//                soundManager.playSoundEffect("TELEPORT");
//                break;
//            case "EXPLODE":
//                if (player.getExplodeMeter() == 3) {
//                    soundManager.playSoundEffect("EXPLOSION");
//                    playerControls.triggerExplosion(player, entityMap, effectManager);
//                    player.setExplodeMeter(0);
//                }
//                break;
//            default:
//        }
//    }

    // Get Player Controls
    public PlayerControls getPlayerControls() {
        return playerControls;
    }

    // Set Player Controls
    public void setPlayerControls(PlayerControls playerControls) {
        this.playerControls = playerControls;
    }
}
