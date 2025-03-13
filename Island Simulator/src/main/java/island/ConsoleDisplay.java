package island;

import island.util.EntityType;

import java.util.Map;

public class ConsoleDisplay {
    private final Island island;
    private final SimulationStatistics statistics;

    public ConsoleDisplay(Island island, SimulationStatistics statistics) {
        this.island = island;
        this.statistics = statistics;
    }

    public void displayStatistics() {
        System.out.println("=".repeat(80));
        System.out.println("Simulation - Tick #" + statistics.getCurrentTick());
        System.out.println("=".repeat(80));
        
        Map<EntityType, Integer> stats = statistics.getStatistics();
        
        System.out.println("Island statistics:");
        for (EntityType type : EntityType.values()) {
            int count = stats.getOrDefault(type, 0);
            System.out.printf("%s %s: %d%n", type.getIcon(), type.name(), count);
        }
        
        System.out.println("-".repeat(80));
        System.out.println("Total animals: " + statistics.getTotalAnimals());
        System.out.println("Total plants: " + stats.getOrDefault(EntityType.PLANT, 0));
        System.out.println("-".repeat(80));
    }
    
    public void displayIslandMap() {
        Location[][] locations = island.getLocations();
        
        for (int row = 0; row < island.getHeight(); row++) {
            for (int col = 0; col < island.getWidth(); col++) {
                System.out.print(locations[row][col]);
            }
            System.out.println();
        }
    }
}