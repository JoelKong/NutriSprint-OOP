package com.mygdx.game.GameLayer.Sound;

public class SoundFactory {
    // Factory method to retrieve sound effects
    private enum SoundType {
        EXPLOSION, TELEPORT, PLAYERHIT, PLAYERDEATH, COLLECTCHERRY, GAINHEALTH,
        BUTTONCLICK, COLLECTPOINTS
    }

    protected String generateSoundEffect(String soundEffect) {
        SoundType soundType = getSoundType(soundEffect);
        switch (soundType) {
            case EXPLOSION:
                return SoundEffects.EXPLOSION;
            case TELEPORT:
                return SoundEffects.TELEPORT;
            case PLAYERHIT:
                return SoundEffects.PLAYERHIT;
            case PLAYERDEATH:
                return SoundEffects.PLAYERDEATH;
            case COLLECTCHERRY:
                return SoundEffects.COLLECTCHERRY;
            case GAINHEALTH:
                return SoundEffects.GAINHEALTH;
            case BUTTONCLICK:
                return SoundEffects.BUTTONCLICK;
            case COLLECTPOINTS:
                return SoundEffects.COLLECTPOINTS;
            default:
                System.out.println("Warning: Unknown soundType when creating a new sound object.");
                return null;
        }
    }

    // Factory method to retrieve background music
    protected String generateBackgroundMusic(String backgroundMusic) {
        switch (backgroundMusic) {
            case "MENU":
                return BackgroundSounds.MENU;
            case "GAMEOVER":
                return BackgroundSounds.GAMEOVER;
            default:
                return null;
        }
    }

    // Takes in string, returns Enum value
    protected SoundType getSoundType(String soundString) {
        // Convert the provided string to uppercase to match enum values
        soundString = soundString.toUpperCase();

        // Check if the provided type matches any of the enum values
        try {
            return SoundType.valueOf(soundString);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided type is not a valid enum value
            System.out.println("Invalid Button type: " + soundString);
            return null;
        }
    }
}
