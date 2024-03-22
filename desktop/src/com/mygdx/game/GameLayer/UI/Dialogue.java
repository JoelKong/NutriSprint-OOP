package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import org.w3c.dom.Text;

public class Dialogue extends Table {
    private Label label;

    public Dialogue(String text) {
//        super();
//        setBackground(createBackgroundDrawable());
//        label = new Label(text, new Label.LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), null));
//        this.add(label).expand().fill();
//        // Call this method after adding to stage if you want to set sizes based on stage size or other components

        super();
        setBackground(createSolidColorBackgroundDrawable(Color.DARK_GRAY)); // Use any color you like
        label = new Label(text, new Label.LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE));
        this.add(label).expand().fill();
    }

    private Drawable createBackgroundDrawable() {
        Texture texture = new Texture(Gdx.files.internal("UI/terra-mother/raw/window.9.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        NinePatch patch = new NinePatch(new TextureRegion(texture), 1, 1, 1, 1);
        return new NinePatchDrawable(patch);
    }

    private Drawable createSolidColorBackgroundDrawable(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888); // Create a 1x1 Pixmap
        pixmap.setColor(color); // Set the color
        pixmap.fill(); // Fill the pixmap with the selected color

        Texture texture = new Texture(pixmap); // Create a texture from the pixmap
        pixmap.dispose(); // Dispose of the pixmap to free memory

        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    // Method to update the text
    public void setText(String newText) {
        label.setText(newText);
    }

    // Use this method to dynamically adjust the padding around the label
    public void setLabelPadding(float top, float left, float bottom, float right) {
        clearChildren(); // Clear the previous label (and any other actors)
        add(label).pad(top, left, bottom, right).expand().fill();
        invalidateHierarchy(); // Important to update the layout after changes
    }

    // Example method to set the width and height separately
    public void setDialogueSize(float width, float height) {
        setSize(width, height); // Set the table size
        // You might want to call pack() to apply the size changes based on the contents, or adjust layout policies accordingly
    }

    public void setDialogueInvisible() {
        this.setVisible(false);
    }

    public void setDialogueVisible() {
        this.setVisible(true);
    }
}
