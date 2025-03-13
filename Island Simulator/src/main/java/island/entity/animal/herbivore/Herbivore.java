package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.entity.plant.Plant;
import island.util.EatingTable;
import island.util.EntityType;
import island.util.RandomGenerator;

import java.util.List;

public abstract class Herbivore extends Animal {

    public Herbivore(EntityType type, Location location) {
        super(type, location);
    }

    @Override
    public void eat() {
        if (!isAlive() || getLocation() == null) {
            return;
        }

        double requiredFood = getFoodRequired();
        double eatenFood = 0;

        if (getType() == EntityType.DUCK) {
            eatenFood += tryToEatCaterpillars(requiredFood - eatenFood);
        }

        if (eatenFood < requiredFood) {
            eatenFood += tryToEatPlants(requiredFood - eatenFood);
        }

        if (eatenFood > 0) {
            increaseSatiation(eatenFood);
        }
    }

    private double tryToEatPlants(double requiredFood) {
        if (requiredFood <= 0) {
            return 0;
        }

        Location location = getLocation();
        List<Plant> plants = location.getPlants();

        if (plants.isEmpty()) {
            return 0;
        }

        int probability = EatingTable.getEatingProbability(getType(), EntityType.PLANT);
        if (!RandomGenerator.getProbability(probability)) {
            return 0;
        }

        double eatenFood = 0;
        for (Plant plant : plants) {
            if (eatenFood >= requiredFood) {
                break;
            }

            double plantWeight = plant.getWeight();
            if (plantWeight <= 0) {
                continue;
            }

            double toEat = Math.min(plantWeight, requiredFood - eatenFood);
            plant.reduceWeight(toEat);
            eatenFood += toEat;

            if (plant.getWeight() <= 0) {
                location.removePlant(plant);
            }
        }

        return eatenFood;
    }

    private double tryToEatCaterpillars(double requiredFood) {
        if (requiredFood <= 0 || getType() != EntityType.DUCK) {
            return 0;
        }

        Location location = getLocation();
        List<Animal> caterpillars = location.getAnimalsByType(EntityType.CATERPILLAR);

        if (caterpillars.isEmpty()) {
            return 0;
        }

        int probability = EatingTable.getEatingProbability(getType(), EntityType.CATERPILLAR);
        if (!RandomGenerator.getProbability(probability)) {
            return 0;
        }

        double eatenFood = 0;
        for (Animal caterpillar : caterpillars) {
            if (eatenFood >= requiredFood) {
                break;
            }

            double caterpillarWeight = caterpillar.getWeight();
            eatenFood += caterpillarWeight;
            caterpillar.die();

            if (eatenFood >= requiredFood) {
                break;
            }
        }

        return eatenFood;
    }
}