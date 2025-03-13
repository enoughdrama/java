package island;

import island.config.SimulationParameters;
import island.entity.animal.Animal;
import island.entity.animal.factory.AnimalFactory;
import island.entity.plant.Plant;
import island.util.EntityType;
import island.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Island {
    private final int width;
    private final int height;
    private final Location[][] locations;
    private final SimulationParameters parameters;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();

    public Island(SimulationParameters parameters) {
        this.parameters = parameters;
        this.width = parameters.getIslandWidth();
        this.height = parameters.getIslandHeight();
        this.locations = new Location[height][width];
        
        initializeLocations();
        populateIsland();
    }
    
    private void initializeLocations() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                locations[row][col] = new Location(row, col);
            }
        }
    }
    
    private void populateIsland() {
        for (EntityType type : EntityType.values()) {
            if (type == EntityType.PLANT) {
                populatePlants(type);
            } else {
                populateAnimals(type);
            }
        }
    }
    
    private void populateAnimals(EntityType type) {
        int count = parameters.getInitialPopulation(type);
        
        for (int i = 0; i < count; i++) {
            int row = RandomGenerator.getRandomNumber(0, height - 1);
            int col = RandomGenerator.getRandomNumber(0, width - 1);
            
            Location location = getLocation(row, col);
            Animal animal = AnimalFactory.createAnimal(type, location);
            
            if (location.addAnimal(animal)) {
                animals.add(animal);
            }
        }
    }
    
    private void populatePlants(EntityType type) {
        int count = parameters.getInitialPopulation(type);
        
        for (int i = 0; i < count; i++) {
            int row = RandomGenerator.getRandomNumber(0, height - 1);
            int col = RandomGenerator.getRandomNumber(0, width - 1);
            
            Location location = getLocation(row, col);
            Plant plant = new Plant(location);
            
            location.addPlant(plant);
            plants.add(plant);
        }
    }
    
    public Location getLocation(int row, int col) {
        if (isValidLocation(row, col)) {
            return locations[row][col];
        }
        return null;
    }
    
    public boolean isValidLocation(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public SimulationParameters getParameters() {
        return parameters;
    }
    
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
    
    public List<Plant> getPlants() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                plants.addAll(locations[row][col].getPlants());
            }
        }
        return new ArrayList<>(plants);
    }
    
    public Location[][] getLocations() {
        return locations;
    }
    
    public void removeDeadAnimals() {
        animals.removeIf(animal -> !animal.isAlive());
    }
    
    public int countAnimalsByType(EntityType type) {
        return (int) animals.stream()
                .filter(animal -> animal.getType() == type && animal.isAlive())
                .count();
    }
    
    public int countPlants() {
        int count = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                count += locations[row][col].getPlants().size();
            }
        }
        return count;
    }
}