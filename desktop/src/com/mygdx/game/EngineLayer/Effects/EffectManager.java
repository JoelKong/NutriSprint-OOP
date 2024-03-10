package com.mygdx.game.EngineLayer.Effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EffectManager {
    private List<Effect> effects;

    public EffectManager() {
        this.effects = new ArrayList<>();
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public void updateEffects() {
        Iterator<Effect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.updateEffect(Gdx.graphics.getDeltaTime());
            if (!effect.isEffectAlive()) {
                iterator.remove();
            }
        }
    }

    public void drawEffects(SpriteBatch batch) {
        for (Effect effect : effects) {
            effect.draw(batch);
        }
    }

    public void disposeEffects() {
        for (Effect effect : effects) {
            effect.disposeEffect();
        }
        effects.clear();
    }
}
