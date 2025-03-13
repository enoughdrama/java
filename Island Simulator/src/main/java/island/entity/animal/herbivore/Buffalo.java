package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Buffalo extends Herbivore {

    public Buffalo(Location location) {
        super(EntityType.BUFFALO, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Buffalo(location);
    }
}