package com.mygdx.game.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// Custom Text Button class
public class CustomTextButton extends TextButton {
    // Parameterized constructor to initialise custom text button based off text
    public CustomTextButton(String text) {
        super(text, new Skin(Gdx.files.internal("UI/libgdx/uiskin.json")));
        initializeButton();
    }

    // Add event listener to button
    private void initializeButton() {
        this.addListener(new ClickListener() {
            // click listener need to do something about it
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Start Button", "Button click alr");
            }
        });
    }
}
