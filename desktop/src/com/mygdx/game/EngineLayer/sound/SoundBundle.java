package com.mygdx.game.EngineLayer.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which stores a list of possible sounds to play.
 * <p>
 * Sub-classes should extend this class and provide specific sound
 * effects for playing.
 */
public abstract class SoundBundle<T extends Enum<T>> implements Disposable {
    // Cache the existing sounds to reduce memory usage
    private Map<T, Sound> soundsCache = new HashMap<>();
    protected void playSound(Sound sound) {
        sound.play();
    }
    
    /** Gets the path to the specified {@code soundType}. */
    abstract FileHandle getSoundFile(T soundType);
    
    /** Plays the specified {@code soundType}'s sound. */
    public void playSound(T soundType) {
        // Check if there's an existing sound type
        if (soundsCache.containsKey(soundType)) {
            // Add to the sounds cache otherwise
            soundsCache.put(soundType, Gdx.audio.newSound(getSoundFile(soundType)));
        }
        playSound(soundsCache.get(soundType));
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        // Dispose all sounds in the cache
        soundsCache.values().forEach(Sound::dispose);
    }
}
