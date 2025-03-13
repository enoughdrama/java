package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EatingTable;
import island.util.EntityType;
import island.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class Predator extends Animal {

    public Predator(EntityType type, Location location) {
        super(type, location);
    }

    @Override
    public void eat() {
        if (!isAlive() || getLocation() == null) {
            return;
        }

        double requiredFood = getFoodRequired();
        double eatenFood = 0;

        Location location = getLocation();
        List<Animal> potentialPrey = new ArrayList<>(location.getAnimals());
        potentialPrey.remove(this);

        for (Animal prey : potentialPrey) {
            if (eatenFood >= requiredFood || !prey.isAlive()) {
                continue;
            }

            EntityType preyType = prey.getType();
            int probability = EatingTable.getEatingProbability(getType(), preyType);

            if (probability > 0 && RandomGenerator.getProbability(probability)) {
                double preyWeight = prey.getWeight();
                eatenFood += preyWeight;
                prey.die();
            }
        }

        if (eatenFood > 0) {
            increaseSatiation(eatenFood);
        }
    }
}