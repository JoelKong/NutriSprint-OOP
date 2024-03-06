package com.mygdx.game.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CustomTextButton extends TextButton {
    public CustomTextButton(String text) {
        super(text, new Skin(Gdx.files.internal("UI/libgdx/uiskin.json")));
        initialize();
    }

    private void initialize() {
        this.addListener(new ClickListener() {
            // click listener need to do something about it
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Start Button", "Button click alr");
            }
        });
    }
}
