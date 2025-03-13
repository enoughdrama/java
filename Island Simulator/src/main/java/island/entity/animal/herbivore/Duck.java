package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Duck extends Herbivore {

    public Duck(Location location) {
        super(EntityType.DUCK, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Duck(location);
    }
}