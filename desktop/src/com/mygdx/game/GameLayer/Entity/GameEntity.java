package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EngineLayer.EngineGameEntity;
import com.mygdx.game.GameLayer.Levels.Levels;
import java.util.Random;

// GameEntity abstract class
public abstract class GameEntity extends EngineGameEntity implements Cloneable {
    // Declare Attributes
    private Texture texture;

    // Default Constructor to player character
    protected GameEntity() {
        super();
    }

    // Parameterized Constructor
    protected GameEntity(Levels level) {
        super();
        setSpeed(level.getPlayerSpeed());
    }

    // All children must have a draw method
    abstract protected void draw(SpriteBatch sb);

    // All entities should be able to update their hitbox upon moving
    protected void updateEntityHitbox() {
        Rectangle hitbox = this.getHitbox();
        hitbox.setX(getPosX());
        hitbox.setY(getPosY());
        this.setHitbox(hitbox);
    }

    @Override
    // Deep Clone GameEntity to reinitialise random x and y and hitbox
    protected GameEntity clone() throws CloneNotSupportedException {
        GameEntity cloned = (GameEntity) super.clone();
        Random random = new Random();
        cloned.setPosX(random.nextInt(Gdx.graphics.getWidth() - 100));
        cloned.setPosY(random.nextInt(Gdx.graphics.getHeight() - 100));
        cloned.setHitbox(new Rectangle(cloned.getPosX(), cloned.getPosY(), cloned.getWidth(), cloned.getHeight()));
        return cloned;
    }

    // Get Texture
    public Texture getTexture() {
        return texture;
    }

    // Set Texture
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
