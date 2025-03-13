package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Bear extends Predator {

    public Bear(Location location) {
        super(EntityType.BEAR, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Bear(location);
    }
}