package com.mygdx.game.GameLayer.Sound;

public class SoundFactory {
    // Factory method to retrieve sound effects
    public String generateSoundEffect(String soundEffect) {
        switch (soundEffect) {
            case "EXPLOSION":
                return SoundEffects.EXPLOSION;
            case "TELEPORT":
                return SoundEffects.TELEPORT;
            case "PLAYERHIT":
                return SoundEffects.PLAYERHIT;
            case "PLAYERDEATH":
                return SoundEffects.PLAYERDEATH;
            case "COLLECTCHERRY":
                return SoundEffects.COLLECTCHERRY;
            case "GAINHEALTH":
                return SoundEffects.GAINHEALTH;
            case "BUTTONCLICK":
                return SoundEffects.BUTTONCLICK;
            case "COLLECTPOINTS":
                return SoundEffects.COLLECTPOINTS;
            default:
                return null;
        }
    }

    // Factory method to retrieve background music
    public String generateBackgroundMusic(String backgroundMusic) {
        switch (backgroundMusic) {
            case "MENU":
                return BackgroundSounds.MENU;
            case "GAMEOVER":
                return BackgroundSounds.GAMEOVER;
            default:
                return null;
        }
    }
}
