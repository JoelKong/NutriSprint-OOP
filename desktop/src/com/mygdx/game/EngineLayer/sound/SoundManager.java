package com.mygdx.game.EngineLayer.sound;

import com.badlogic.gdx.utils.Disposable;

/**
 * Manager for handling audio-related functionality.
 * <p>
 * This manager should be created via its {@link SoundManager.Factory}.
 * @param <B> Generic for the {@link SoundBundle} to use.
 * @param <T> Generic for the {@link SoundBundle}'s enum of possible sound types.
 */
public class SoundManager<B extends SoundBundle<T>, T extends Enum<T>> implements Disposable {
    private final B bundle;
    
    SoundManager(B bundle) {
        this.bundle = bundle;
    }

    public B getBundle() {
        return bundle;
    }

    /** Factory interface to instantiate a {@link SoundManager}. */
    public interface Factory<B extends SoundBundle<T>, T extends Enum<T>> {
        SoundManager<B, T> createSoundManager();
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
