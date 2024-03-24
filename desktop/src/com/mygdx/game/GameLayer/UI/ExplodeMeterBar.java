package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ExplodeMeterBar {
    private ProgressBar explodeMeterBar;
    private Skin skin;
    private int maxExplodeMeter;

    protected ExplodeMeterBar(Skin skin) {
        this.maxExplodeMeter = 3;
        this.skin = skin;
        createExplodeMeterBar();
    }

    protected void createExplodeMeterBar() {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        Drawable backgroundDrawable = skin.newDrawable("white", Color.GRAY);
        backgroundDrawable.setMinHeight(30f);
        progressBarStyle.background = backgroundDrawable;

        Drawable knobBeforeDrawable = skin.newDrawable("white", Color.RED);
        knobBeforeDrawable.setMinHeight(30f);
        progressBarStyle.knobBefore = knobBeforeDrawable;

        explodeMeterBar = new ProgressBar(0f, maxExplodeMeter, 1f, false, progressBarStyle);
        explodeMeterBar.setValue(maxExplodeMeter);
    }

    protected void updateExplodeMeterValue(int explodeMeterValue) {
        explodeMeterBar.setValue(explodeMeterValue);
    }

    protected ProgressBar getExplodeMeterBar() {
        return explodeMeterBar;
    }
}
