package island.util;

import java.util.HashMap;
import java.util.Map;

public class EatingTable {
    private static final Map<EntityType, Map<EntityType, Integer>> eatingProbabilities = new HashMap<>();

    static {
        initPredatorEatingProbabilities();
    }

    private static void initPredatorEatingProbabilities() {
        // Wolf eating probabilities
        Map<EntityType, Integer> wolfProbabilities = new HashMap<>();
        wolfProbabilities.put(EntityType.HORSE, 10);
        wolfProbabilities.put(EntityType.DEER, 15);
        wolfProbabilities.put(EntityType.RABBIT, 60);
        wolfProbabilities.put(EntityType.MOUSE, 80);
        wolfProbabilities.put(EntityType.GOAT, 60);
        wolfProbabilities.put(EntityType.SHEEP, 70);
        wolfProbabilities.put(EntityType.BOAR, 15);
        wolfProbabilities.put(EntityType.BUFFALO, 10);
        wolfProbabilities.put(EntityType.DUCK, 40);
        eatingProbabilities.put(EntityType.WOLF, wolfProbabilities);

        // Boa eating probabilities
        Map<EntityType, Integer> boaProbabilities = new HashMap<>();
        boaProbabilities.put(EntityType.FOX, 15);
        boaProbabilities.put(EntityType.RABBIT, 20);
        boaProbabilities.put(EntityType.MOUSE, 40);
        boaProbabilities.put(EntityType.DUCK, 10);
        eatingProbabilities.put(EntityType.BOA, boaProbabilities);

        // Fox eating probabilities
        Map<EntityType, Integer> foxProbabilities = new HashMap<>();
        foxProbabilities.put(EntityType.RABBIT, 70);
        foxProbabilities.put(EntityType.MOUSE, 90);
        foxProbabilities.put(EntityType.DUCK, 60);
        foxProbabilities.put(EntityType.CATERPILLAR, 40);
        eatingProbabilities.put(EntityType.FOX, foxProbabilities);

        // Bear eating probabilities
        Map<EntityType, Integer> bearProbabilities = new HashMap<>();
        bearProbabilities.put(EntityType.BOA, 80);
        bearProbabilities.put(EntityType.HORSE, 40);
        bearProbabilities.put(EntityType.DEER, 80);
        bearProbabilities.put(EntityType.RABBIT, 80);
        bearProbabilities.put(EntityType.MOUSE, 90);
        bearProbabilities.put(EntityType.GOAT, 70);
        bearProbabilities.put(EntityType.SHEEP, 70);
        bearProbabilities.put(EntityType.BOAR, 50);
        bearProbabilities.put(EntityType.BUFFALO, 20);
        bearProbabilities.put(EntityType.DUCK, 10);
        eatingProbabilities.put(EntityType.BEAR, bearProbabilities);

        // Eagle eating probabilities
        Map<EntityType, Integer> eagleProbabilities = new HashMap<>();
        eagleProbabilities.put(EntityType.FOX, 10);
        eagleProbabilities.put(EntityType.RABBIT, 90);
        eagleProbabilities.put(EntityType.MOUSE, 90);
        eagleProbabilities.put(EntityType.DUCK, 80);
        eatingProbabilities.put(EntityType.EAGLE, eagleProbabilities);

        // Duck eating caterpillar probability (special case)
        Map<EntityType, Integer> duckProbabilities = new HashMap<>();
        duckProbabilities.put(EntityType.CATERPILLAR, 90);
        eatingProbabilities.put(EntityType.DUCK, duckProbabilities);

        // All herbivores eat plants with 100% probability
        Map<EntityType, Integer> plantEaters = new HashMap<>();
        plantEaters.put(EntityType.PLANT, 100);
        
        eatingProbabilities.put(EntityType.HORSE, plantEaters);
        eatingProbabilities.put(EntityType.DEER, plantEaters);
        eatingProbabilities.put(EntityType.RABBIT, plantEaters);
        eatingProbabilities.put(EntityType.MOUSE, plantEaters);
        eatingProbabilities.put(EntityType.GOAT, plantEaters);
        eatingProbabilities.put(EntityType.SHEEP, plantEaters);
        eatingProbabilities.put(EntityType.BOAR, plantEaters);
        eatingProbabilities.put(EntityType.BUFFALO, plantEaters);
        eatingProbabilities.put(EntityType.CATERPILLAR, plantEaters);
    }

    public static int getEatingProbability(EntityType predator, EntityType prey) {
        Map<EntityType, Integer> preyMap = eatingProbabilities.get(predator);
        if (preyMap == null) {
            return 0;
        }
        return preyMap.getOrDefault(prey, 0);
    }
}