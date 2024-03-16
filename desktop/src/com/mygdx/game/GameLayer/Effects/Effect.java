package com.mygdx.game.GameLayer.Effects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

// Effect class to handle game effects
public class Effect {
    private Vector2 position;
    private Texture texture;
    private float lifespan;
    private float width;
    private float height;

    public Effect(Vector2 position, Texture texture, float width, float height, float lifespan) {
        this.position = position;
        this.texture = texture;
        this.lifespan = lifespan;
        this.width = width;
        this.height= height;
    }

    // Updating of game effect based off its lifespan
    public void updateEffect(float deltaTime) {
        lifespan -= deltaTime;
        if (lifespan <= 0) {
            disposeEffect();
        }
    }

    // Draw effect
    public void draw(SpriteBatch batch) {
        if (isEffectAlive()) {
            batch.draw(texture, position.x, position.y, width, height);
        }
    }

    // Check if the effect is alive
    public boolean isEffectAlive() {
        return lifespan > 0;
    }

    // Dispose the texture effect if it should no longer be used
    public void disposeEffect() {
        texture.dispose();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getLifespan() {
        return lifespan;
    }

    public void setLifespan(float lifespan) {
        this.lifespan = lifespan;
    }
}

