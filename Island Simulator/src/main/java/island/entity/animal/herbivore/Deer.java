package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Deer extends Herbivore {

    public Deer(Location location) {
        super(EntityType.DEER, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Deer(location);
    }
}