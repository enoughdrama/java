package island;

import island.config.AnimalParameters;
import island.entity.animal.Animal;
import island.entity.plant.Plant;
import island.util.EntityType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Location {
    private final int row;
    private final int col;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public synchronized boolean addAnimal(Animal animal) {
        EntityType type = animal.getType();
        int maxCount = AnimalParameters.getMaxCount(type);
        
        if (getAnimalCountByType(type) >= maxCount) {
            return false;
        }
        
        animals.add(animal);
        animal.setLocation(this);
        return true;
    }
    
    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
    
    public synchronized void addPlant(Plant plant) {
        if (plants.size() < AnimalParameters.getMaxCount(EntityType.PLANT)) {
            plants.add(plant);
        }
    }
    
    public synchronized void removePlant(Plant plant) {
        plants.remove(plant);
    }
    
    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
    
    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }
    
    public List<Animal> getAnimalsByType(EntityType type) {
        return animals.stream()
                .filter(animal -> animal.getType() == type && animal.isAlive())
                .collect(Collectors.toList());
    }
    
    public int getAnimalCountByType(EntityType type) {
        return (int) animals.stream()
                .filter(animal -> animal.getType() == type && animal.isAlive())
                .count();
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    @Override
    public String toString() {
        if (!animals.isEmpty()) {
            return animals.get(0).toString();
        } else if (!plants.isEmpty()) {
            return plants.get(0).toString();
        }
        return " ";
    }
}