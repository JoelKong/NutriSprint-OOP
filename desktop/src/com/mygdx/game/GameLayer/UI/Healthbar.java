package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

// Our player health system
public class Healthbar extends Group {
    private Texture heartTexture;
    private final int maxHealth = 10;
    private int currentHealth;

    // Initialising our current and max health, texture and also updating it
    protected Healthbar() {
        this.currentHealth = maxHealth;
        heartTexture = new Texture(Gdx.files.internal("heart.png"));
        updateHealthDisplay();
    }

    // Update the health based off the new health
    protected void updateHealth(int newHealth) {
        this.currentHealth = newHealth;
        updateHealthDisplay();
    }

    protected void updateHealthDisplay() {
        this.clearChildren(); // Remove all existing heart images
        float heartWidth = 48;
        float padding = 5;
        float startX = maxHealth * (heartWidth + padding); // Start at the rightmost position

        for (int i = 0; i < currentHealth; i++) {
            Image heart = new Image(heartTexture);
            heart.setSize(heartWidth, heartWidth); // Set the size of the heart icon
            // Position hearts from the right to the left
            float xPosition = startX - ((i + 1) * (heartWidth + padding));
            heart.setPosition(xPosition, 0);
            this.addActor(heart); // Add the heart to the group
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Here you could add any logic that needs to run each frame
    }

    protected void dispose() {
        heartTexture.dispose(); // Dispose of the heart texture
    }
}

