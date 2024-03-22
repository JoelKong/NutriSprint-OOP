package com.mygdx.game.GameLayer.UI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HUD {
    private StyledLabel scoreLabel;
    private StyledLabel levelLabel;
    private Skin skin;
    private Stage uiStage;
    private Table hudTable;
    private Table dialogueTable;
    private Healthbar healthbar;
    private TeleportCooldownBar teleportCooldownBar;
    private ExplodeMeterBar explodeMeterBar;
    private ScoreTable scoreTable;
    private InstructionDialog dialogue;

    public HUD(Stage uiStage) {
        this.uiStage = uiStage;
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        // Initialize the hudTable
        this.hudTable = new Table();
        this.hudTable.setFillParent(true);
        this.hudTable.top();

        // Initialize the dialogue
        this.dialogue = new InstructionDialog(new String[]{"text"});

        // Initialize the score and level labels
        this.levelLabel = new StyledLabel("");

        // Initialize the health bar with the maximum health and teleportCooldownBar
        this.healthbar = new Healthbar();
        this.teleportCooldownBar = new TeleportCooldownBar(skin, 5000f);
        this.explodeMeterBar = new ExplodeMeterBar(skin);

        // Initialise the score + objective table
        this.scoreTable = new ScoreTable(skin);

        // Adding existing elements to the hudTable
        hudTable.add(levelLabel).align(Align.left).padTop(10).padLeft(20).expandX();
        hudTable.add(scoreTable).expandX().align(Align.center).padTop(0); // Expand to use extra horizontal space
        hudTable.add(healthbar).align(Align.right).padTop(10).padRight(300).size(240, 24); // Fixed size for health bar
        hudTable.row(); // Move to the next row

        // Span the progress bar under the health bar by using colspan(2) to skip the first two columns
        hudTable.add(); // This empty cell will take the place of the level label column
        hudTable.add(); // This empty cell will take the place of the score label column
        hudTable.add(teleportCooldownBar.getProgressBar()).align(Align.right).padTop(4).padRight(20).size(400, 24); // Set size to match health bar
        hudTable.row();

        hudTable.add();
        hudTable.add();
        hudTable.add(explodeMeterBar.getExplodeMeterBar()).align(Align.right).padTop(20).padRight(20).size(400, 24);
        hudTable.row();

        hudTable.add();
        hudTable.add(this.dialogue).expand().bottom();
        hudTable.add();
        hudTable.row();

        // Add the hudTable to the uiStage
        uiStage.addActor(hudTable);
    }

    public void updateHudScore (int score) {
        scoreTable.updateScore(score);
    }

    public void updateHudObjective(String newObjective) {
        scoreTable.updateObjective(newObjective);
    }

    public void updateHudHealth(int health) {
        // Directly update the healthbar which is now part of the HUD
        this.healthbar.updateHealth(health);
    }

    public void updateHudExplodeMeterCount(int explodeMeterCount) {
        this.explodeMeterBar.updateExplodeMeterValue(explodeMeterCount);
    }

    public void updateHudLevel(String level) {
        levelLabel.setText(level);
    }

    public void updateHudTeleportCooldown(int teleportCooldown, int maxTeleportCooldown) {
        this.teleportCooldownBar.updateCooldownValue(teleportCooldown);
    }

    public void draw() {
        uiStage.act(Gdx.graphics.getDeltaTime());
        uiStage.draw();
    }

    // Getters and Setters
    public StyledLabel getScoreLabel() {
        return scoreLabel;
    }
    public void setScoreLabel(StyledLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public StyledLabel getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(StyledLabel levelLabel) {
        this.levelLabel = levelLabel;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public void setUiStage(Stage uiStage) {
        this.uiStage = uiStage;
    }

    public Table getHudTable() {
        return hudTable;
    }

    public void setHudTable(Table hudTable) {
        this.hudTable = hudTable;
    }

    public Healthbar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(Healthbar healthbar) {
        this.healthbar = healthbar;
    }

    public void dispose() {
        healthbar.dispose();
        uiStage.dispose();
        skin.dispose();
    }

}
