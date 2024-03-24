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
    private Healthbar healthbar;
    private DebugHealthBar debugHealthBar;
    private TeleportCooldownBar teleportCooldownBar;
    private ExplodeMeterBar explodeMeterBar;
    private ScoreTable scoreTable;
    private Dialogue dialogue;

    protected HUD(Stage uiStage, String[] dialogue) {
        this.uiStage = uiStage;
        // this.uiStage.setDebugAll(true);
        this.skin = new Skin(Gdx.files.internal("UI/libgdx/uiskin.json"));

        // Initialize the hudTable
        this.hudTable = new Table();
        this.hudTable.setDebug(true);
        this.hudTable.setFillParent(true);
        this.hudTable.top();

        Table dialogueTable = new Table();
        dialogueTable.setFillParent(true);
        dialogueTable.bottom();

        // Initialize the dialogue
        this.dialogue = new Dialogue("");
        this.dialogue.setPosition(Gdx.graphics.getWidth() / 2f - 150, Gdx.graphics.getHeight() / 2f - 75); // Center the dialogue on the screen
        this.dialogue.setLabelPadding(20, 20, 20, 20); // Optional: Adjust the label padding within the dialogue

        // Initialize the score and level labels
        this.levelLabel = new StyledLabel("");

        // Initialize the health bar with the maximum health and teleportCooldownBar
        this.healthbar = new Healthbar();
        this.debugHealthBar = new DebugHealthBar(skin, 10);
        this.teleportCooldownBar = new TeleportCooldownBar(skin, 5000f);
        this.explodeMeterBar = new ExplodeMeterBar(skin);

        // Initialise the score + objective table
        this.scoreTable = new ScoreTable(skin);

        Table groupTable = new Table();
        groupTable.add(debugHealthBar.getProgressBar()).width(500).padBottom(50);
        groupTable.row();
        groupTable.add(teleportCooldownBar.getProgressBar()).width(500).padBottom(50);
        groupTable.row();
        groupTable.add(explodeMeterBar.getExplodeMeterBar()).width(500);

        // HudTable for Level Label, ScoreTable & Healthbar
        hudTable.add(levelLabel).uniform().align(Align.topLeft).expandX();
        hudTable.add(scoreTable).uniform().expandX();
        hudTable.add(groupTable).uniform().align(Align.topRight).expandX();
        hudTable.row();

        // Span the progress bar under the health bar by using colspan(2) to skip the first two columns
//
//        hudTable.add();
//        hudTable.add();
//        hudTable.add(explodeMeterBar.getExplodeMeterBar()).align(Align.right).padTop(20).padRight(20).size(400, 24);
//        hudTable.row();

//        hudTable.add();
//        hudTable.add(this.dialogue).expand().align(Align.bottom);
//        hudTable.add();
//        hudTable.row();

        dialogueTable.add().uniform().expandX();
        dialogueTable.add(this.dialogue).uniform().expandX();
        dialogueTable.add().uniform().expandX();

        // Add the hudTable to the uiStage
        uiStage.addActor(hudTable);
        uiStage.addActor(dialogueTable);
    }

    public void updateHudScore(int score) {
        scoreTable.updateScore(score);
    }

    protected void updateHudObjective(String newObjective) {
        scoreTable.updateObjective(newObjective);
    }

    public void updateHudHealth(int health) {
        // Directly update the healthbar which is now part of the HUD
        this.healthbar.updateHealth(health);
    }

    public void updateDebugHealthBar(int health) {
        this.debugHealthBar.updateHealthValue(health);
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
