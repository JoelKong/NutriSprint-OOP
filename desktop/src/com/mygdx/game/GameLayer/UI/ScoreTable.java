package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class ScoreTable extends Table {
    private StyledLabel scoreLabel;
    private StyledLabel objectiveLabel;

    public ScoreTable(Skin skin) {
        super(skin);
        this.setBackground("default-window"); // Make sure your skin has this drawable

        // Initialize score and objective labels
        scoreLabel = new StyledLabel("Score: 0");
        objectiveLabel = new StyledLabel("Objective: 5");

        // Configure the table
        this.pad(10);
        this.defaults().pad(5);
        this.add(scoreLabel).expandX().fillX().align(Align.center).row();
        this.add(objectiveLabel).expandX().fillX().align(Align.center);
        this.pack(); // Automatically size the table based on its contents
    }

    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void updateObjective(String objective) {
        objectiveLabel.setText("Objective: " + objective);
    }
}
