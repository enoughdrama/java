package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Caterpillar extends Herbivore {

    public Caterpillar(Location location) {
        super(EntityType.CATERPILLAR, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Caterpillar(location);
    }
}