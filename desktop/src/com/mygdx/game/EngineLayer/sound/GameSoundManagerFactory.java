package com.mygdx.game.EngineLayer.sound;

// SoundManager factory which uses the example sound bundle implementation
public class GameSoundManagerFactory implements SoundManager.Factory<GameSoundBundle, GameSoundBundle.SoundType> {
    @Override
    public SoundManager<GameSoundBundle, GameSoundBundle.SoundType> createSoundManager() {
        return new SoundManager<>(new GameSoundBundle());
    }
}
