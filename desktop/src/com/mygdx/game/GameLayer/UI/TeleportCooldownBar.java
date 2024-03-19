package com.mygdx.game.GameLayer.UI;

// TeleportCooldownBar.java

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TeleportCooldownBar {
    private ProgressBar progressBar;
    private Skin skin;
    private float maxTeleportCooldown;

    public TeleportCooldownBar(Skin skin, float maxTeleportCooldown) {
        this.skin = skin;
        this.maxTeleportCooldown = maxTeleportCooldown;
        createProgressBar();
    }

    private void createProgressBar() {
        // Create the style for the progress bar
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        Drawable backgroundDrawable = skin.newDrawable("white", Color.GRAY);
        backgroundDrawable.setMinHeight(20f); // Adjust the height as needed
        progressBarStyle.background = backgroundDrawable;

        Drawable knobBeforeDrawable = skin.newDrawable("white", Color.BLUE);
        knobBeforeDrawable.setMinHeight(20f); // Adjust the height as needed
        progressBarStyle.knobBefore = knobBeforeDrawable;

        // Create the progress bar
        progressBar = new ProgressBar(0f, maxTeleportCooldown, 1f, false, progressBarStyle);
        progressBar.setValue(maxTeleportCooldown); // Initialize with the max cooldown
    }

    protected void updateCooldownValue(int teleportCooldown) {
        progressBar.setValue(Math.min(teleportCooldown, maxTeleportCooldown));
    }

    protected ProgressBar getProgressBar() {
        return progressBar;
    }
}

