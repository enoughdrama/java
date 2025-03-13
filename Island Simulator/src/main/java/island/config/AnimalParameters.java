package island.config;

import island.util.EntityType;

import java.util.HashMap;
import java.util.Map;

public class AnimalParameters {
    private static final Map<EntityType, Double> maxWeight = new HashMap<>();
    private static final Map<EntityType, Integer> maxCount = new HashMap<>();
    private static final Map<EntityType, Integer> speed = new HashMap<>();
    private static final Map<EntityType, Double> foodRequired = new HashMap<>();
    
    static {
        initParameters();
    }
    
    private static void initParameters() {
        // Weight in kg
        maxWeight.put(EntityType.WOLF, 50.0);
        maxWeight.put(EntityType.BOA, 15.0);
        maxWeight.put(EntityType.FOX, 8.0);
        maxWeight.put(EntityType.BEAR, 500.0);
        maxWeight.put(EntityType.EAGLE, 6.0);
        maxWeight.put(EntityType.HORSE, 400.0);
        maxWeight.put(EntityType.DEER, 300.0);
        maxWeight.put(EntityType.RABBIT, 2.0);
        maxWeight.put(EntityType.MOUSE, 0.05);
        maxWeight.put(EntityType.GOAT, 60.0);
        maxWeight.put(EntityType.SHEEP, 70.0);
        maxWeight.put(EntityType.BOAR, 400.0);
        maxWeight.put(EntityType.BUFFALO, 700.0);
        maxWeight.put(EntityType.DUCK, 1.0);
        maxWeight.put(EntityType.CATERPILLAR, 0.01);
        maxWeight.put(EntityType.PLANT, 1.0);
        
        // Max count per location
        maxCount.put(EntityType.WOLF, 30);
        maxCount.put(EntityType.BOA, 30);
        maxCount.put(EntityType.FOX, 30);
        maxCount.put(EntityType.BEAR, 5);
        maxCount.put(EntityType.EAGLE, 20);
        maxCount.put(EntityType.HORSE, 20);
        maxCount.put(EntityType.DEER, 20);
        maxCount.put(EntityType.RABBIT, 150);
        maxCount.put(EntityType.MOUSE, 500);
        maxCount.put(EntityType.GOAT, 140);
        maxCount.put(EntityType.SHEEP, 140);
        maxCount.put(EntityType.BOAR, 50);
        maxCount.put(EntityType.BUFFALO, 10);
        maxCount.put(EntityType.DUCK, 200);
        maxCount.put(EntityType.CATERPILLAR, 1000);
        maxCount.put(EntityType.PLANT, 200);
        
        // Movement speed in cells per turn
        speed.put(EntityType.WOLF, 3);
        speed.put(EntityType.BOA, 1);
        speed.put(EntityType.FOX, 2);
        speed.put(EntityType.BEAR, 2);
        speed.put(EntityType.EAGLE, 3);
        speed.put(EntityType.HORSE, 4);
        speed.put(EntityType.DEER, 4);
        speed.put(EntityType.RABBIT, 2);
        speed.put(EntityType.MOUSE, 1);
        speed.put(EntityType.GOAT, 3);
        speed.put(EntityType.SHEEP, 3);
        speed.put(EntityType.BOAR, 2);
        speed.put(EntityType.BUFFALO, 3);
        speed.put(EntityType.DUCK, 4);
        speed.put(EntityType.CATERPILLAR, 0);
        speed.put(EntityType.PLANT, 0);
        
        // Food required (kg) for satiation
        foodRequired.put(EntityType.WOLF, 8.0);
        foodRequired.put(EntityType.BOA, 3.0);
        foodRequired.put(EntityType.FOX, 2.0);
        foodRequired.put(EntityType.BEAR, 80.0);
        foodRequired.put(EntityType.EAGLE, 1.0);
        foodRequired.put(EntityType.HORSE, 60.0);
        foodRequired.put(EntityType.DEER, 50.0);
        foodRequired.put(EntityType.RABBIT, 0.45);
        foodRequired.put(EntityType.MOUSE, 0.01);
        foodRequired.put(EntityType.GOAT, 10.0);
        foodRequired.put(EntityType.SHEEP, 15.0);
        foodRequired.put(EntityType.BOAR, 50.0);
        foodRequired.put(EntityType.BUFFALO, 100.0);
        foodRequired.put(EntityType.DUCK, 0.15);
        foodRequired.put(EntityType.CATERPILLAR, 0.0);
    }
    
    public static double getMaxWeight(EntityType type) {
        return maxWeight.getOrDefault(type, 0.0);
    }
    
    public static int getMaxCount(EntityType type) {
        return maxCount.getOrDefault(type, 0);
    }
    
    public static int getSpeed(EntityType type) {
        return speed.getOrDefault(type, 0);
    }
    
    public static double getFoodRequired(EntityType type) {
        return foodRequired.getOrDefault(type, 0.0);
    }
}