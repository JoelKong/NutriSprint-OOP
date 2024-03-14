package com.mygdx.game.EngineLayer.Sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.EngineLayer.Levels.Levels;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, Sound> soundEffects;
    private Map<String, Music> backgroundMusic;

    public SoundManager() {
        soundEffects = new HashMap<>();
        backgroundMusic = new HashMap<>();
    }

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
            case "SHOOTCHICKEN":
                return SoundEffects.SHOOTCHICKEN;
            default:
                return null;
        }
    }

    public void loadSoundEffect(String[] effects) {
        for (String soundEffect: effects) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(getSoundEffect(soundEffect)));
            if (sound != null) {
                soundEffects.put(soundEffect, sound);
            }
        }
    }

    public void playSoundEffect(String effect) {
        Sound sound = soundEffects.get(effect);
        if (sound != null) {
            sound.play();
        }
    }

    public void loadBackgroundMusic(Levels levelAssets) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(levelAssets.getLevelMusic()));
        backgroundMusic.put(levelAssets.getLevelTitle(), music);
    }

    public void playBackgroundMusic(String bg, boolean loop) {
        Music music = backgroundMusic.get(bg);
        if (music != null) {
        music.setLooping(loop);
        music.play();
        }
    }

    public void stopBackgroundMusic(String bgMusic) {
        Music music = backgroundMusic.get(bgMusic);
        if (music != null && music.isPlaying()) {
            music.stop();
        }
    }

    public void disposeSounds() {
        for (Sound sound : soundEffects.values()) {
            sound.dispose();
        }
        for (Music music : backgroundMusic.values()) {
            music.dispose();
        }
    }
}
