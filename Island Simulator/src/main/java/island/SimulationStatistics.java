package island;

import island.util.EntityType;

import java.util.HashMap;
import java.util.Map;

public class SimulationStatistics {
    private final Island island;
    private int currentTick;
    private final Map<EntityType, Integer> statistics = new HashMap<>();

    public SimulationStatistics(Island island) {
        this.island = island;
        this.currentTick = 0;
    }

    public void collectStatistics() {
        currentTick++;
        
        for (EntityType type : EntityType.values()) {
            if (type == EntityType.PLANT) {
                statistics.put(type, island.countPlants());
            } else {
                statistics.put(type, island.countAnimalsByType(type));
            }
        }
    }
    
    public int getCurrentTick() {
        return currentTick;
    }
    
    public Map<EntityType, Integer> getStatistics() {
        return new HashMap<>(statistics);
    }
    
    public int getEntityCount(EntityType type) {
        return statistics.getOrDefault(type, 0);
    }
    
    public int getTotalAnimals() {
        int count = 0;
        for (EntityType type : EntityType.values()) {
            if (type != EntityType.PLANT) {
                count += getEntityCount(type);
            }
        }
        return count;
    }
}