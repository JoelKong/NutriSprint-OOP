package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// Custom Text Button class
public class CustomTextButton extends TextButton {
    // Parameterized constructor to initialise custom text button based off input
    protected CustomTextButton(String text, Runnable onClickAction) {
        super(text, new Skin(Gdx.files.internal("UI/terra-mother/skin/terra-mother-ui.json")));
        this.pad(20);
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
