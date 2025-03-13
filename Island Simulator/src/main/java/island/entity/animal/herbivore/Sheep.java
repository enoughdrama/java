package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Sheep extends Herbivore {

    public Sheep(Location location) {
        super(EntityType.SHEEP, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Sheep(location);
    }
}