package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Healthbar extends Group {
    private Texture heartTexture;
    private final int maxHealth;
    private int currentHealth;

    public Healthbar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth; // Assuming full health initially
        heartTexture = new Texture(Gdx.files.internal("heart.png")); // Load your heart icon

        updateHealthDisplay();
    }

    public void updateHealth(int newHealth) {
        this.currentHealth = newHealth;
        updateHealthDisplay();
    }

    private void updateHealthDisplay() {
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

    public void dispose() {
        heartTexture.dispose(); // Dispose of the heart texture
    }
}

