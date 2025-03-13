package island;

import island.config.SimulationParameters;
import island.entity.animal.Animal;
import island.entity.plant.Plant;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationRunner {
    private final Island island;
    private final SimulationParameters parameters;
    private final SimulationStatistics statistics;
    private final ConsoleDisplay display;
    private final ScheduledExecutorService scheduler;
    private boolean isRunning = false;

    public SimulationRunner(SimulationParameters parameters) {
        this.parameters = parameters;
        this.island = new Island(parameters);
        this.statistics = new SimulationStatistics(island);
        this.display = new ConsoleDisplay(island, statistics);
        this.scheduler = Executors.newScheduledThreadPool(3);
    }

    public void startSimulation() {
        if (isRunning) {
            return;
        }
        
        isRunning = true;
        
        scheduler.scheduleAtFixedRate(
                this::runAnimalLifecycle,
                0,
                parameters.getTickDurationMs(),
                TimeUnit.MILLISECONDS
        );
        
        scheduler.scheduleAtFixedRate(
                this::runPlantGrowth,
                0,
                parameters.getTickDurationMs(),
                TimeUnit.MILLISECONDS
        );
        
        scheduler.scheduleAtFixedRate(
                this::runStatisticsCollection,
                0,
                parameters.getTickDurationMs(),
                TimeUnit.MILLISECONDS
        );
    }
    
    private void runAnimalLifecycle() {
        List<Animal> animals = island.getAnimals();
        
        try (var executor = Executors.newWorkStealingPool()) {
            for (Animal animal : animals) {
                if (animal.isAlive()) {
                    executor.submit(() -> {
                        animal.eat();
                        animal.move(island);
                        animal.reproduce(island);
                        animal.decreaseSatiation();
                        return null;
                    });
                }
            }
        }
        
        island.removeDeadAnimals();
    }
    
    private void runPlantGrowth() {
        Location[][] locations = island.getLocations();
        
        try (var executor = Executors.newWorkStealingPool()) {
            for (int row = 0; row < island.getHeight(); row++) {
                for (int col = 0; col < island.getWidth(); col++) {
                    Location location = locations[row][col];
                    executor.submit(() -> {
                        Plant.growPlants(location);
                        return null;
                    });
                }
            }
        }
    }
    
    private void runStatisticsCollection() {
        statistics.collectStatistics();
        display.displayStatistics();
        
        if (parameters.isStopWhenNoAnimals() && statistics.getTotalAnimals() == 0) {
            stopSimulation();
        }
        
        if (statistics.getCurrentTick() >= parameters.getSimulationDuration()) {
            stopSimulation();
        }
    }
    
    public void stopSimulation() {
        if (!isRunning) {
            return;
        }
        
        isRunning = false;
        scheduler.shutdownNow();
        
        System.out.println("Simulation completed after " + statistics.getCurrentTick() + " ticks.");
    }
    
    public Island getIsland() {
        return island;
    }
    
    public SimulationStatistics getStatistics() {
        return statistics;
    }
}