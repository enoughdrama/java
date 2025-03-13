package island.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    private static final Random random = new Random();

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static boolean getProbability(int percentageProbability) {
        return ThreadLocalRandom.current().nextInt(100) < percentageProbability;
    }
    
    public static Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[ThreadLocalRandom.current().nextInt(directions.length)];
    }
}