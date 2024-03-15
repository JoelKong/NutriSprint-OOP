package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class WindowButton extends ImageTextButton {

    public WindowButton(String text, Runnable onClickAction) {
        super(text, createStyle());
        initializeButton(onClickAction);
    }

    private static ImageTextButtonStyle createStyle() {
        Texture texture = new Texture(Gdx.files.internal("UI/terra-mother/raw/window.9.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);

        NinePatch patch = new NinePatch(new TextureRegion(texture), 1, 1, 1, 1);
        Drawable drawable = new NinePatchDrawable(patch);

        BitmapFont font = new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        ImageTextButtonStyle style = new ImageTextButtonStyle();
        style.up = drawable;
        style.down = drawable;
        style.font = font;

        return style;
    }

    private void initializeButton(Runnable onClickAction) {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClickAction.run();
            }
        });

        this.pad(20);
        this.padTop(20);
        this.padBottom(20);
        this.padLeft(25);
        this.padRight(25);
    }
}
