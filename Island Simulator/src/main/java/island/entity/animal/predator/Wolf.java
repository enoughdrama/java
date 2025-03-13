package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Wolf extends Predator {

    public Wolf(Location location) {
        super(EntityType.WOLF, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Wolf(location);
    }
}