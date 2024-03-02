package com.mygdx.game.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Button extends TextButton {
    private static final BitmapFont font = new BitmapFont(); // Shared font for all buttons

    public Button(String text, Color upColor, Color downColor, float width, float height) {
        super(text, createButtonStyle(upColor, downColor, width, height));
        setSize(width, height);
    }

    private static TextButtonStyle createButtonStyle(Color upColor, Color downColor, float width, float height) {
        TextButtonStyle style = new TextButtonStyle();
        style.up = createDrawable(upColor, (int) width, (int) height);
        style.down = createDrawable(downColor, (int) width, (int) height);
        style.font = font;
        return style;
    }

    private static Drawable createDrawable(Color color, int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap); // Create a texture from pixmap
        pixmap.dispose();
        // Wrap the texture in a TextureRegion before creating the drawable
        TextureRegion textureRegion = new TextureRegion(texture);
        return new TextureRegionDrawable(textureRegion);
    }
}

