package island.entity.plant;

import island.Location;
import island.config.AnimalParameters;
import island.util.EntityType;
import island.util.RandomGenerator;

import java.util.UUID;

public class Plant {
    private final UUID id;
    private double weight;
    private final Location location;

    public Plant(Location location) {
        this.id = UUID.randomUUID();
        this.weight = AnimalParameters.getMaxWeight(EntityType.PLANT);
        this.location = location;
    }

    public void grow() {
        weight = Math.min(weight * 1.1, AnimalParameters.getMaxWeight(EntityType.PLANT));
    }

    public void reduceWeight(double amount) {
        weight -= amount;
        if (weight < 0) {
            weight = 0;
        }
    }

    public UUID getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public static void growPlants(Location location) {
        if (RandomGenerator.getProbability(30)) {
            int maxPlants = AnimalParameters.getMaxCount(EntityType.PLANT);
            int currentPlants = location.getPlants().size();
            
            if (currentPlants < maxPlants) {
                Plant newPlant = new Plant(location);
                location.addPlant(newPlant);
            }
        }
        
        for (Plant plant : location.getPlants()) {
            plant.grow();
        }
    }

    @Override
    public String toString() {
        return EntityType.PLANT.getIcon();
    }
}