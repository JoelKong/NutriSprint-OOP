package com.mygdx.game.GameLayer.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class InstructionDialog extends StyledLabel {
    private String dialogue[];
    private StyledLabel label;

    public InstructionDialog(String dialogue[]) {
        super(dialogue[0]);
    };

}
