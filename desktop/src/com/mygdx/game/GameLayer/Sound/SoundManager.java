package com.mygdx.game.GameLayer.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLayer.Levels.Levels;
import java.util.HashMap;
import java.util.Map;

// Contains data for all our sounds
public class SoundManager {
    private Map<String, Sound> soundEffects;
    private Map<String, Music> backgroundMusic;

    public SoundManager() {
        soundEffects = new HashMap<>();
        backgroundMusic = new HashMap<>();
    }

    // Factory method to retrieve sound effects
    public String getSoundEffect(String soundEffect) {
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
    public String getBackgroundMusic(String backgroundMusic) {
        switch (backgroundMusic) {
            case "MENU":
                return BackgroundSounds.MENU;
            case "GAMEOVER":
                return BackgroundSounds.GAMEOVER;
            default:
                return null;
        }
    }

    // Load sound effect resources
    public void loadSoundEffect(String[] effects) {
        for (String soundEffect: effects) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(getSoundEffect(soundEffect)));
            if (sound != null) {
                soundEffects.put(soundEffect, sound);
            }
        }
    }

    // Play sound effect
    public void playSoundEffect(String effect) {
        Sound sound = soundEffects.get(effect);
        if (sound != null) {
            sound.play();
        }
    }

    // Load BG music resources
    public void loadBackgroundMusic(String bgType) {
        Music bgMusic = Gdx.audio.newMusic(Gdx.files.internal(getBackgroundMusic(bgType)));
        if (bgMusic != null) {
            backgroundMusic.put(bgType, bgMusic);
        }
    }

    // Overload method to load bg music with level specifications
    public void loadBackgroundMusic(Levels levelAssets) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(levelAssets.getLevelMusic()));
        backgroundMusic.put(levelAssets.getLevelTitle(), music);
    }

    // Play bg music
    public void playBackgroundMusic(String bg, boolean loop) {
        Music music = backgroundMusic.get(bg);
        if (music != null) {
        music.setLooping(loop);
        music.play();
        }
    }

    // Stop bg music
    public void stopBackgroundMusic(String bgMusic) {
        Music music = backgroundMusic.get(bgMusic);
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }

    // Dispose all sounds
    public void disposeSounds() {
        for (Sound sound : soundEffects.values()) {
            sound.dispose();
        }
        for (Music music : backgroundMusic.values()) {
            music.dispose();
        }
    }

    // Getters and Setters
    public Map<String, Sound> getSoundEffects() {
        return soundEffects;
    }

    public void setSoundEffects(Map<String, Sound> soundEffects) {
        this.soundEffects = soundEffects;
    }

    public Map<String, Music> getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(Map<String, Music> backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }
}
