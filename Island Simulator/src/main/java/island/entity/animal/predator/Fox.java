package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Fox extends Predator {

    public Fox(Location location) {
        super(EntityType.FOX, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Fox(location);
    }
}