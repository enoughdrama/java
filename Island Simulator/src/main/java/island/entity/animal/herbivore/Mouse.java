package island.entity.animal.herbivore;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Mouse extends Herbivore {

    public Mouse(Location location) {
        super(EntityType.MOUSE, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Mouse(location);
    }
}