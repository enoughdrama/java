package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Boar extends Herbivore {

    public Boar(Location location) {
        super(EntityType.BOAR, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Boar(location);
    }
}