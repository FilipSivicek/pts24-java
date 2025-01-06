package sk.uniba.fmph.dcs.game_board;

public final class Throw implements RandomInterface {
    private final int diceSides;

    public Throw() {
        this.diceSides = 6;
    }

    public Throw(final int diceSides) {
        if (diceSides < 1) {
            throw new RuntimeException();
        }
        this.diceSides = diceSides;
    }

    private int randomDice() {
        return (int) ((Math.random() % diceSides) + 1);
    }

    @Override
    public int randomInt(int bound) {
        return 0;
    }

    @Override
    public int[] randomArray(int dices) {
        int[] ans = new int[dices];
        for (int i = 0; i < dices; i++) {
            ans[i] = randomDice();
        }
        return ans;
    }
}
