package com.mygdx.game.EngineLayer.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

// TODO: Remove
public class GameSoundBundle extends SoundBundle<GameSoundBundle.SoundType> {
    /**
     * Possible sounds that this bundle can play.
     */
    public enum SoundType {
        EnemyDeath("enemy-death.mp3"),
        PlayerJump("player-jump.mp3"),
        EnemySpawn("enemy-spawn.mp3");

        SoundType(String fileName) {
            this.fileName = fileName;
        }

        final String fileName;
    }

    @Override
    FileHandle getSoundFile(SoundType soundType) {
        return Gdx.files.internal("audio/" + soundType.fileName);
    }

    /**
     * Plays a {@link SoundType#EnemyDeath} sound.
     */
    public void playDeathSound() {
        playSound(SoundType.EnemyDeath);
    }

    /**
     * Plays a {@link SoundType#PlayerJump} sound.
     */
    public void playJumpSound() {
        playSound(SoundType.PlayerJump);
    }

    /**
     * Plays a {@link SoundType#EnemySpawn} sound.
     */
    public void playSpawnSound() {
        playSound(SoundType.EnemySpawn);
    }
}
