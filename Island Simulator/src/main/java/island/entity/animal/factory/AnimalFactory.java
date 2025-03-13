package island.entity.animal.factory;

import island.Location;
import island.entity.animal.Animal;
import island.entity.animal.herbivore.*;
import island.entity.animal.predator.*;
import island.util.EntityType;

public class AnimalFactory {
    
    public static Animal createAnimal(EntityType type, Location location) {
        return switch (type) {
            case WOLF -> new Wolf(location);
            case BOA -> new Boa(location);
            case FOX -> new Fox(location);
            case BEAR -> new Bear(location);
            case EAGLE -> new Eagle(location);
            case HORSE -> new Horse(location);
            case DEER -> new Deer(location);
            case RABBIT -> new Rabbit(location);
            case MOUSE -> new Mouse(location);
            case GOAT -> new Goat(location);
            case SHEEP -> new Sheep(location);
            case BOAR -> new Boar(location);
            case BUFFALO -> new Buffalo(location);
            case DUCK -> new Duck(location);
            case CATERPILLAR -> new Caterpillar(location);
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}