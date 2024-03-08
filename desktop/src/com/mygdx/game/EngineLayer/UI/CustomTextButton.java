package com.mygdx.game.EngineLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// Custom Text Button class
public class CustomTextButton extends TextButton {
    // Parameterized constructor to initialise custom text button based off input
    public CustomTextButton(String text, Runnable onClickAction) {
        super(text, new Skin(Gdx.files.internal("UI/libgdx/uiskin.json")));
        initializeButton(onClickAction);
    }

    // Add event listener to button with a functionality mapped to the button
    private void initializeButton(Runnable onClickAction) {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClickAction.run();
            }
        });
    }
}
