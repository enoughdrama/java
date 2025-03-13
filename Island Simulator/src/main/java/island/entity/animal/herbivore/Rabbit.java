package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Rabbit extends Herbivore {

    public Rabbit(Location location) {
        super(EntityType.RABBIT, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Rabbit(location);
    }
}