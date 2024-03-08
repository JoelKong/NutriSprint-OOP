package com.mygdx.game.EngineLayer.sound;

import com.badlogic.gdx.utils.Disposable;

/**
 * Manager for handling audio-related functionality.
 * <p>
 * This manager should be created via its {@link SoundManager.Factory}.
 */
public class SoundManager<T extends Enum<T>> implements Disposable {
    private final SoundBundle<T> bundle;
    
    SoundManager(SoundBundle<T> bundle) {
        this.bundle = bundle;
    }
    
    /** Factory interface to instantiate a {@link SoundManager}. */
    public interface Factory<T extends Enum<T>> {
        SoundManager<T> createSoundManager();
    }
    
    public void playSound(T soundType) {
        bundle.playSound(soundType);
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        // Dispose the SoundBundle's sounds
        bundle.dispose();
    }
}
