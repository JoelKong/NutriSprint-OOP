package com.mygdx.game.GameLayer.Entity;
import com.mygdx.game.EngineLayer.EngineEntity.EngineGameEntityFactory;
import com.mygdx.game.GameLayer.Levels.Levels;

public class EntityFactory extends EngineGameEntityFactory {
    private enum EntityType {
        ISAAC, FRENCHFRIES, ROCK, APPLE, BANANA, BURGER, CHICKEN, CHERRY, VEGETABLE
    }

    // Getter for EntityType enum
    protected EntityType getEntityType(String type) {
        // Convert the provided string to uppercase to match enum values
        type = type.toUpperCase();

        // Check if the provided type matches any of the enum values
        try {
            return EntityType.valueOf(type);
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided type is not a valid enum value
            System.out.println("Invalid entity type: " + type);
            return null;
        }
    }

    // Factory function to create GameEntity objects
    protected GameEntity createEntity(String entityName, Levels level) {
        EntityType entityType = getEntityType(entityName);

        switch (entityType) {
            case ISAAC:
                return new Isaac(level);
            case FRENCHFRIES:
                return new FrenchFries(level);
            case ROCK:
                return new Rock(level);
            case APPLE:
                return new Apple(level);
            case VEGETABLE:
                return new Vegetable(level);
            case BANANA:
                return new Banana(level);
            case BURGER:
                return new Burger(level);
            case CHICKEN:
                return new Chicken(level);
            case CHERRY:
                return new Cherry(level);
            default:
                System.out.println("Warning: Unknown entityType when creating a new entity object.");
                return null;
        }
    }
}
