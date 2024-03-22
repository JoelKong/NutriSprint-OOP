package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InstructionDialog extends Dialog {
    private float desiredWidth;
    private float desiredHeight;
    private Stage stage;
    private Texture imageTexture;
    private Image image;

    public InstructionDialog(String title, Skin skin, Stage stage, float widthPercentage, float heightPercentage) {
        super(title, skin);

        if (Gdx.files.internal("high_apple.png").exists()) {
            this.imageTexture = new Texture(Gdx.files.internal("high_apple.png"));
            this.image = new Image(imageTexture);

            getContentTable().add(this.image).center().padTop(10f).width(desiredWidth).height(desiredHeight * 0.3f); // Adjust the size as needed
            getContentTable().row(); // Start a new row for the following content

            // Create a label with word wrap enabled
            Label label = new Label("Test...\n\n" +
                    "- Do this...\n" +
                    "- Do that...\n" +
                    "- Respect others.", skin);
            desiredWidth = stage.getWidth() * widthPercentage;
            desiredHeight = stage.getHeight() * heightPercentage;

            // Add the label to the content table with padding
            getContentTable().add(label).width(desiredWidth).pad(20); // 20 units of padding on all sides

            // Set up the button
            // this.button("Next");
            this.stage = stage;

            // Set the size of the dialog here if you want it to be fixed
            // setSize is now within enforceSizeAndPosition method called in show()
            this.desiredHeight = stage.getHeight() * heightPercentage;

            // Call enforceSizeAndPosition to apply the size and position
            enforceSizeAndPosition();
        } else {
            Gdx.app.error("InstructionDialog", "Image file not found!");
        }
    }

    @Override
    public Dialog show(Stage stage) {
        super.show(stage);

        // We need to call this after the super.show to ensure the width and height have been calculated
        stage.addActor(this);
        this.setSize(desiredWidth, desiredHeight);

        // This positions the dialog at the bottom center of the stage
        this.setPosition((stage.getWidth() - this.getWidth()) / 2, 0);

        return this;
    }

    @Override
    public void layout() {
        super.layout();
        // Override layout to ensure size is set as per our desired width and height
        this.setSize(desiredWidth, desiredHeight);
        // After laying out, we ensure the position is set to bottom
        this.setPosition((stage.getWidth() - this.getWidth()) / 2, 0);
    }

    private void enforceSizeAndPosition() {
        // Set the dialog size and position
        setSize(desiredWidth, desiredHeight);
        setPosition((stage.getWidth() - getWidth()) / 2, 0); // Center horizontally, anchor to bottom
    }

    @Override
    protected void result(Object object) {
        // Handle the result from the button click here
        // The object parameter corresponds to the button clicked
        Gdx.app.log("Instruction Dialog", "The player has acknowledged the instructions.");
    }
}
