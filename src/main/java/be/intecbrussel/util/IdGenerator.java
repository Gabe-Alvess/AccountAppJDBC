package be.intecbrussel.util;

import java.util.Random;

public class IdGenerator {
    private static long maxID = 5_000_000L;
    public static long generateId() {
        Random random = new Random();
        return random.nextLong(maxID) + 1;
    }
}
