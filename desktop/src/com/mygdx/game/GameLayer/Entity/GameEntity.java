package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameLayer.Levels.Levels;

import java.util.Random;

// GameEntity abstract class
public abstract class GameEntity implements Cloneable {
    // Declare Attributes
    private Texture texture;
    private String entityType;
    private float xPosition;
    private float yPosition;
    private float speed;
    private int width;
    private int height;
    private Rectangle hitbox;

    // Default Constructor to player character
    protected GameEntity() {
        this.xPosition = (Gdx.graphics.getWidth() - width) / 2f;
        this.yPosition = (Gdx.graphics.getHeight() - height) / 2f;
        this.speed = 300;
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
    }

    // Parameterized Constructor
    protected GameEntity(Levels level) {
        this.xPosition = (Gdx.graphics.getWidth() - width) / 2f;
        this.yPosition = (Gdx.graphics.getHeight() - height) / 2f;
        this.speed = level.getPlayerSpeed();
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
    }

    // All entities should be able to update their hitbox upon moving
    protected void updateEntityHitbox() {
        Rectangle hitbox = this.getHitbox();
        hitbox.setX(this.xPosition);
        hitbox.setY(this.yPosition);
        this.setHitbox(hitbox);
    }

    // All children must have a draw method
    abstract protected void draw(SpriteBatch sb);

    @Override
    // Deep Clone GameEntity to reinitialise random x and y and hitbox
    protected GameEntity clone() throws CloneNotSupportedException {
            GameEntity cloned = (GameEntity) super.clone();
            Random random = new Random();
            cloned.xPosition = random.nextInt(Gdx.graphics.getWidth() - 100);
            cloned.yPosition = random.nextInt(Gdx.graphics.getHeight() - 100);
            cloned.hitbox = new Rectangle(cloned.xPosition, cloned.yPosition, cloned.width, cloned.height);
            return cloned;
    }

    // Get Entity Type
    public String getEntityType() {
        return entityType;
    }

    // Set Entity Type
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    // Get Texture
    public Texture getTexture() {
        return texture;
    }

    // Set Texture
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    // Get PosX
    public float getPosX() {
        return xPosition;
    }

    // Set PosX
    public void setPosX(float x) {
        this.xPosition = x;
    }

    // Get PosY
    public float getPosY() {
        return yPosition;
    }

    // Set PosY
    public void setPosY(float y) {
        this.yPosition = y;
    }

    // Get Speed
    public float getSpeed() {
        return speed;
    }

    // Set Speed
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // Get Width
    public int getWidth() {
        return width;
    }

    // Set Width
    public void setWidth(int width) {
        this.width = width;
    }

    // Get Height
    public int getHeight() {
        return height;
    }

    // Set Height
    public void setHeight(int height) {
        this.height = height;
    }

    // Get Hitbox
    public Rectangle getHitbox() {
        return hitbox;
    }

    // Set Hitbox
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
