package sk.uniba.fmph.dcs.game_board;

import java.util.Random;

public class Rand implements RandomInterface {
    Random random;

    public Rand() {
        random = new Random();
    }

    @Override
    public int randomInt(int bound) {
        return random.nextInt(bound);
    }

    @Override
    public int[] randomArray(int arrayLength) {
        return null;
    }
}
