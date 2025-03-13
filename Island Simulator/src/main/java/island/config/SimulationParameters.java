package island.config;

import island.util.EntityType;

import java.util.HashMap;
import java.util.Map;

public class SimulationParameters {
    private int islandWidth = 100;
    private int islandHeight = 20;
    private int simulationDuration = 100;
    private int tickDurationMs = 1000;
    private boolean stopWhenNoAnimals = true;
    private final Map<EntityType, Integer> initialPopulation = new HashMap<>();
    private final Map<EntityType, Integer> maxPerLocation = new HashMap<>();
    private final Map<EntityType, Integer> offspring = new HashMap<>();

    public SimulationParameters() {
        initDefaultParameters();
    }

    private void initDefaultParameters() {
        // Initial population
        initialPopulation.put(EntityType.WOLF, 30);
        initialPopulation.put(EntityType.BOA, 30);
        initialPopulation.put(EntityType.FOX, 30);
        initialPopulation.put(EntityType.BEAR, 5);
        initialPopulation.put(EntityType.EAGLE, 20);
        initialPopulation.put(EntityType.HORSE, 20);
        initialPopulation.put(EntityType.DEER, 20);
        initialPopulation.put(EntityType.RABBIT, 150);
        initialPopulation.put(EntityType.MOUSE, 500);
        initialPopulation.put(EntityType.GOAT, 140);
        initialPopulation.put(EntityType.SHEEP, 140);
        initialPopulation.put(EntityType.BOAR, 50);
        initialPopulation.put(EntityType.BUFFALO, 10);
        initialPopulation.put(EntityType.DUCK, 200);
        initialPopulation.put(EntityType.CATERPILLAR, 1000);
        initialPopulation.put(EntityType.PLANT, 200);

        // Max per location
        maxPerLocation.put(EntityType.WOLF, 30);
        maxPerLocation.put(EntityType.BOA, 30);
        maxPerLocation.put(EntityType.FOX, 30);
        maxPerLocation.put(EntityType.BEAR, 5);
        maxPerLocation.put(EntityType.EAGLE, 20);
        maxPerLocation.put(EntityType.HORSE, 20);
        maxPerLocation.put(EntityType.DEER, 20);
        maxPerLocation.put(EntityType.RABBIT, 150);
        maxPerLocation.put(EntityType.MOUSE, 500);
        maxPerLocation.put(EntityType.GOAT, 140);
        maxPerLocation.put(EntityType.SHEEP, 140);
        maxPerLocation.put(EntityType.BOAR, 50);
        maxPerLocation.put(EntityType.BUFFALO, 10);
        maxPerLocation.put(EntityType.DUCK, 200);
        maxPerLocation.put(EntityType.CATERPILLAR, 1000);
        maxPerLocation.put(EntityType.PLANT, 200);

        // Offspring per reproduction
        offspring.put(EntityType.WOLF, 3);
        offspring.put(EntityType.BOA, 15);
        offspring.put(EntityType.FOX, 4);
        offspring.put(EntityType.BEAR, 2);
        offspring.put(EntityType.EAGLE, 2);
        offspring.put(EntityType.HORSE, 1);
        offspring.put(EntityType.DEER, 1);
        offspring.put(EntityType.RABBIT, 7);
        offspring.put(EntityType.MOUSE, 7);
        offspring.put(EntityType.GOAT, 1);
        offspring.put(EntityType.SHEEP, 1);
        offspring.put(EntityType.BOAR, 5);
        offspring.put(EntityType.BUFFALO, 1);
        offspring.put(EntityType.DUCK, 5);
        offspring.put(EntityType.CATERPILLAR, 10);
    }

    public int getIslandWidth() {
        return islandWidth;
    }

    public void setIslandWidth(int islandWidth) {
        this.islandWidth = islandWidth;
    }

    public int getIslandHeight() {
        return islandHeight;
    }

    public void setIslandHeight(int islandHeight) {
        this.islandHeight = islandHeight;
    }

    public int getSimulationDuration() {
        return simulationDuration;
    }

    public void setSimulationDuration(int simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public int getTickDurationMs() {
        return tickDurationMs;
    }

    public void setTickDurationMs(int tickDurationMs) {
        this.tickDurationMs = tickDurationMs;
    }

    public boolean isStopWhenNoAnimals() {
        return stopWhenNoAnimals;
    }

    public void setStopWhenNoAnimals(boolean stopWhenNoAnimals) {
        this.stopWhenNoAnimals = stopWhenNoAnimals;
    }

    public Map<EntityType, Integer> getInitialPopulation() {
        return initialPopulation;
    }

    public Map<EntityType, Integer> getMaxPerLocation() {
        return maxPerLocation;
    }

    public Map<EntityType, Integer> getOffspring() {
        return offspring;
    }

    public int getInitialPopulation(EntityType type) {
        return initialPopulation.getOrDefault(type, 0);
    }

    public int getMaxPerLocation(EntityType type) {
        return maxPerLocation.getOrDefault(type, 0);
    }

    public int getOffspring(EntityType type) {
        return offspring.getOrDefault(type, 0);
    }
}