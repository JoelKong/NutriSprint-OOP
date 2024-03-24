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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Dialogue extends Table {
    private Label label;
    private Label instructionLabel;
    private Texture texture;
    private Image image;
    private Table leftTable;
    private Table rightTable;
    private ScrollPane scrollPane;
    private float maxWidth = 800;
    private float maxHeight = 150;

    public Dialogue(String text) {
        super();
        setBackground(createSolidColorBackgroundDrawable(Color.DARK_GRAY));
        this.leftTable = new Table();
        this.rightTable = new Table();

        // Adding an image
        this.texture = new Texture(Gdx.files.internal("high_apple.png"));
        this.image = new Image(texture);
        this.leftTable.add(image).size(300, 300);

        // Label initialization with text wrapping
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("UI/terra-mother/raw/font-export.fnt")), Color.WHITE);
        this.label = new Label(text, labelStyle);
        this.label.setWrap(true);

        // Instruction label
        this.instructionLabel = new Label("Press 'Enter' key to continue...", labelStyle);

        // Wrap the label in a scroll pane
        this.scrollPane = new ScrollPane(label);

        // Add the ScrollPane to the table, NOT the label directly
        this.rightTable.add(scrollPane).size(maxWidth, maxHeight).expand().fill();
        this.rightTable.row();
        this.rightTable.add(instructionLabel).expandX().bottom().right();
        this.add(leftTable);
        this.add(rightTable);

        // Ensuring the table respects the maximum size constraints
        this.setSize(maxWidth, maxHeight + instructionLabel.getHeight());
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

    // Method to dynamically set the padding of the dialogues
    public void setLabelPadding(float top, float left, float bottom, float right) {
        // Create a new background drawable with padding for the label
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.CLEAR); // Transparent color
        pixmap.fill();

        TextureRegionDrawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        // Set the padding on the background drawable
        backgroundDrawable.setLeftWidth(left);
        backgroundDrawable.setRightWidth(right);
        backgroundDrawable.setTopHeight(top);
        backgroundDrawable.setBottomHeight(bottom);

        // Apply the padded background to the label's style
        Label.LabelStyle labelStyle = label.getStyle();
        labelStyle.background = backgroundDrawable;

        // Apply the new style to the label
        label.setStyle(labelStyle);

        // You may need to call invalidate() to ensure the layout updates
        label.invalidate();
        scrollPane.invalidate();
    }


    public void setDialogueInvisible() {
        this.setVisible(false);
    }

    public void setDialogueVisible() {
        this.setVisible(true);
    }
}