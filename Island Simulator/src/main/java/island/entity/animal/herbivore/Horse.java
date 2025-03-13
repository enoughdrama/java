package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Horse extends Herbivore {

    public Horse(Location location) {
        super(EntityType.HORSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Horse(location);
    }
}