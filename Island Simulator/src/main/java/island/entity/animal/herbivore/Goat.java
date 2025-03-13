package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Goat extends Herbivore {

    public Goat(Location location) {
        super(EntityType.GOAT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Goat(location);
    }
}