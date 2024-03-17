package com.mygdx.game.EngineLayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

// What all game entities have in common throughout all types of games
public abstract class EngineGameEntity {
    private String entityType;
    private float xPosition;
    private float yPosition;
    private float speed;
    private int width;
    private int height;
    private Rectangle hitbox;

    protected EngineGameEntity () {
        this.xPosition = (Gdx.graphics.getWidth() - width) / 2f;
        this.yPosition = (Gdx.graphics.getHeight() - height) / 2f;
        this.speed = 300;
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
    }

    // All entities should be able to update their hitbox upon moving
    abstract protected void updateEntityHitbox();

    // Get Entity Type
    public String getEntityType() {
        return entityType;
    }

    // Set Entity Type
    public void setEntityType(String entityType) {
        this.entityType = entityType;
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