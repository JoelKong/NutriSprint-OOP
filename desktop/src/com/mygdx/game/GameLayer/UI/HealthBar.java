package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class HealthBar {
    private ProgressBar progressBar;
    private Skin skin;
    private float maxHealth;

    protected HealthBar(Skin skin, float maxHealth) {
        this.skin = skin;
        this.maxHealth = maxHealth;
        createProgressBar();
    }

    private void createProgressBar() {
        // Create the style for the progress bar
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        Drawable backgroundDrawable = skin.newDrawable("white", Color.GRAY);
        backgroundDrawable.setMinHeight(30f); // Adjust the height as needed
        progressBarStyle.background = backgroundDrawable;

        Drawable knobBeforeDrawable = skin.newDrawable("white", Color.RED);
        knobBeforeDrawable.setMinHeight(30f); // Adjust the height as needed
        progressBarStyle.knobBefore = knobBeforeDrawable;

        // Create the progress bar
        progressBar = new ProgressBar(0f, maxHealth, 1f, false, progressBarStyle);
        progressBar.setValue(maxHealth); // Initialize with the max cooldown
    }

    protected void updateHealthValue(int teleportCooldown) {
        progressBar.setValue(Math.min(teleportCooldown, maxHealth));
    }

    protected ProgressBar getProgressBar() {
        return progressBar;
    }
}

