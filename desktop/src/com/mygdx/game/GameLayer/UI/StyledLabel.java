package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class StyledLabel extends ImageTextButton {

    public StyledLabel(String text) {
        super(text, createButtonStyle());
        this.pad(20);
    }

    private static ImageTextButtonStyle createButtonStyle() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Texture texture = new Texture(Gdx.files.internal("UI/terra-mother/raw/window.9.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        NinePatch patch = new NinePatch(new TextureRegion(texture), 1, 1, 1, 1);
        Drawable drawable = new NinePatchDrawable(patch);

        ImageTextButtonStyle style = new ImageTextButtonStyle();
        style.up = drawable;
        style.down = drawable;
        style.font = font;

        return style;
    }
}
