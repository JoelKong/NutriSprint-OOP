package com.mygdx.game.GameLayer.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameLayer.Levels.Levels;

import java.util.List;

// NPC Class inherited from GameEntity
public class AI extends GameEntity {
    // Declare Attributes
    private int AIID;

    // Default Constructor defaulting to AI
    protected AI() {
        super();
        setTexture(new Texture("Entities/fries.png"));
        setEntityType("FRENCHFRIES");
        this.AIID = 1;
        this.setSpeed(200);
    }

    protected AI(Levels level) {
        super(level);
        setEntityType("FRENCHFRIES");
        setTexture(new Texture(Gdx.files.internal(level.getEnemyTexture().get(0))));
        this.AIID = 1;
        this.setSpeed(level.getEnemySpeed());

    }

    @Override
    // Draw AI
    protected void draw(SpriteBatch sb) {
        sb.draw(this.getTexture(), this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    };

    // AI behavior to chase the player
    protected void behavior(GameEntity targetPlayer) {
        Vector2 currentPosition = new Vector2(this.getPosX(), this.getPosY());
        Vector2 targetPosition = new Vector2(targetPlayer.getPosX(), targetPlayer.getPosY());

        Vector2 direction = targetPosition.sub(currentPosition).nor();

//        float angleAdjustmentDegrees = 20;
//        direction.rotateDeg(MathUtils.randomSign() * angleAdjustmentDegrees);

        Vector2 movement = direction.scl(getSpeed() * Gdx.graphics.getDeltaTime());
        currentPosition.add(movement);

        this.setPosX(currentPosition.x);
        this.setPosY(currentPosition.y);
    }

    // Behavior of some ais that spawn other entities
    protected List<GameEntity> behavior() {
        return null;
    }

    // Get AI ID
    public int getAIID() {
        return AIID;
    }

    // Set AI ID
    public void setAIID(int AIID) {
        this.AIID = AIID;
    }
}
