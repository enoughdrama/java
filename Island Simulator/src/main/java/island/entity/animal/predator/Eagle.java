package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Eagle extends Predator {

    public Eagle(Location location) {
        super(EntityType.EAGLE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Eagle(location);
    }
}