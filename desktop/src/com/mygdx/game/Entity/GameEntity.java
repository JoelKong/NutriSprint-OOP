package com.mygdx.game.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

// GameEntity abstract class
public abstract class GameEntity implements Cloneable {
    // Declare Attributes
    private Texture texture;
    private float xPosition;
    private float yPosition;
    private float speed;
    private int width;
    private int height;
    private Rectangle hitbox;
    private boolean popFromScreen;

    // Default Constructor to player character
    protected GameEntity() {
        this.texture = new Texture(Gdx.files.internal("circle.png"));
        this.xPosition = (Gdx.graphics.getWidth() - width) / 2f;
        this.yPosition = (Gdx.graphics.getHeight() - height) / 2f;
        this.speed = 150;
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
        this.popFromScreen = false;
    }

    // Parameterized Constructor
    protected GameEntity(Texture texture, float xPosition, float yPosition, float speed) {
        this.texture = texture;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.speed = speed;
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
        this.popFromScreen = false;
    }

    // Constructor only accepting texture
    protected GameEntity(Texture texture) {
        Random random = new Random();
        this.texture = texture;
        this.xPosition = random.nextInt(Gdx.graphics.getWidth() - 100);
        this.yPosition = random.nextInt(Gdx.graphics.getHeight() - 100);
        this.speed = 0;
        this.width = 32;
        this.height = 32;
        this.hitbox = new Rectangle(xPosition, yPosition, width, height);
        this.popFromScreen = false;
    }

    // All children must have a draw method
    abstract protected void draw(SpriteBatch sb);

    // Empty movement class to override
    protected void movement() {}

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

    // Get pop from screen
    public boolean getPopFromScreen() {
        return popFromScreen;
    }

    // Set pop from screen
    public void setPopFromScreen(boolean popFromScreen) {
        this.popFromScreen = popFromScreen;
    }
}
