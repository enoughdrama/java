package island.entity.animal.predator;

import island.Location;
import island.entity.animal.Animal;
import island.util.EntityType;

public class Boa extends Predator {

    public Boa(Location location) {
        super(EntityType.BOA, location);
    }

    @Override
    protected Animal createOffspring(Location location) {
        return new Boa(location);
    }
}