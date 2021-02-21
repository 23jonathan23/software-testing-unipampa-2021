import java.util.Random;

public class Randomizador {
    private static final int SEED = 1111;
    private static final Random RAND = new Random();

    public static Random getRandom() {
        return RAND;
    }

    public static void reset() {
        RAND.setSeed(SEED);
    }
}
