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
    private Table progressBarsTable;
    private HealthBar healthBar;
    private TeleportCooldownBar teleportCooldownBar;
    private ExplodeMeterBar explodeMeterBar;
    private ScoreTable scoreTable;
    private Dialogue dialogue;

    protected HUD(Stage uiStage, String[] dialogue) {
        this.uiStage = uiStage;
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        // Initialize the hudTable
        this.hudTable = new Table();
        this.hudTable.setFillParent(true);
        this.hudTable.top();

        this.dialogueTable = new Table();
        this.dialogueTable.setFillParent(true);
        this.dialogueTable.bottom();

        // Initialize the dialogue
        this.dialogue = new Dialogue("");
        this.dialogue.setLabelPadding(20, 20, 20, 20);

        // Initialize the score and level labels
        this.levelLabel = new StyledLabel("");

        // Initialize the health bar with the maximum health and teleportCooldownBar
        this.healthBar = new HealthBar(skin, 10);
        this.teleportCooldownBar = new TeleportCooldownBar(skin, 5000f);
        this.explodeMeterBar = new ExplodeMeterBar(skin);

        // Initialise the score + objective table
        this.scoreTable = new ScoreTable(skin);

        this.progressBarsTable = new Table();
        this.progressBarsTable.add(this.healthBar.getProgressBar()).width(500).padBottom(50);
        this.progressBarsTable.row();
        this.progressBarsTable.add(this.teleportCooldownBar.getProgressBar()).width(500).padBottom(50);
        this.progressBarsTable.row();
        this.progressBarsTable.add(this.explodeMeterBar.getExplodeMeterBar()).width(500);

        // HudTable for Level Label, ScoreTable & Healthbar
        this.hudTable.add(this.levelLabel).uniform().align(Align.topLeft).expandX();
        this.hudTable.add(this.scoreTable).uniform().expandX();
        this.hudTable.add(this.progressBarsTable).uniform().align(Align.topRight).expandX();
        this.hudTable.row();

        this.dialogueTable.add().uniform().expandX();
        this.dialogueTable.add(this.dialogue).uniform().expandX();
        this.dialogueTable.add().uniform().expandX();

        this.uiStage.addActor(this.hudTable);
        this.uiStage.addActor(this.dialogueTable);
    }

    public void updateHudScore(int score) {
        this.scoreTable.updateScore(score);
    }

    protected void updateHudObjective(String newObjective) {
        this.scoreTable.updateObjective(newObjective);
    }

    public void updateHealthBar(int health) {
        this.healthBar.updateHealthValue(health);
    }

    public void updateHudExplodeMeterCount(int explodeMeterCount) {
        this.explodeMeterBar.updateExplodeMeterValue(explodeMeterCount);
    }

    protected void updateHudLevel(String level) {
        levelLabel.setText(level);
    }

    public void updateHudTeleportCooldown(int teleportCooldown, int maxTeleportCooldown) {
        this.teleportCooldownBar.updateCooldownValue(teleportCooldown);
    }

    protected void updateHudDialogue(String text) {
        this.dialogue.setText(text);
    }

    protected void updateHudDialogueVisible() {
        this.dialogue.setDialogueVisible();
    }

    protected void updateHudDialogueInvisible() {
        this.dialogue.setDialogueInvisible();
    }

    protected void draw() {
        this.uiStage.act(Gdx.graphics.getDeltaTime());
        this.uiStage.draw();
    }

    // Getters and Setters
    public StyledLabel getScoreLabel() {
        return this.scoreLabel;
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

    public Table getHudTable() {
        return this.hudTable;
    }

    public void setHudTable(Table hudTable) {
        this.hudTable = hudTable;
    }

    public Table getDialogueTable() {
        return this.dialogueTable;
    }

    public void setDialogueTable(Table dialogueTable) {
        this.dialogueTable = dialogueTable;
    }

    public HealthBar getHealthbar() {
        return this.healthBar;
    }

    public void setHealthbar(HealthBar healthbar) {
        this.healthBar = healthbar;
    }

    public void dispose() {
        this.uiStage.dispose();
        this.skin.dispose();
    }

}
