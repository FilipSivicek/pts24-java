package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.ImmediateEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CivilizationCardDeckTest {

    private ArrayList<EndOfGameEffect> possibleEndOfGameEffects = new ArrayList<>(List.of(EndOfGameEffect.FARMER,
            EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.BUILDER, EndOfGameEffect.SHAMAN, EndOfGameEffect.MEDICINE,
            EndOfGameEffect.ART, EndOfGameEffect.MUSIC, EndOfGameEffect.WRITING, EndOfGameEffect.SUNDIAL,
            EndOfGameEffect.POTTERY, EndOfGameEffect.TRANSPORT, EndOfGameEffect.WEAVING));

    private ArrayList<ImmediateEffect> possibleImmediateEffects = new ArrayList<>(
            List.of(ImmediateEffect.POINT, ImmediateEffect.WOOD, ImmediateEffect.CLAY, ImmediateEffect.STONE,
                    ImmediateEffect.GOLD, ImmediateEffect.FOOD));

    private class RandMock implements RandomInterface {

        private int result = 0;

        public RandMock(int result) {
            this.result = result;
        }

        @Override
        public int randomInt(int bound) {
            return result % bound;
        }

        @Override
        public int[] randomArray(int arrayLength) {
            return new int[0];
        }

        public void setResult(int result) {
            this.result = result;
        }
    }

    @Test
    public void getTopTest() {
        RandMock rand = new RandMock(0);
        CivilizationCardDeck cdc = new CivilizationCardDeck();
        cdc.setRand(rand);

        Optional<CivilisationCard> cc = cdc.getTop();

        assert cc.isEmpty()
                || Arrays.equals(cc.get().immediateEffect(), new ImmediateEffect[] { ImmediateEffect.POINT });
        for (int i = 0; i < possibleEndOfGameEffects.size(); i++) {
            rand.setResult(i);
            cc = cdc.getTop();
            assert cc.isEmpty() || Arrays.equals(cc.get().endOfGameEffect(),
                    new EndOfGameEffect[] { possibleEndOfGameEffects.get(i) });
        }

        cdc = new CivilizationCardDeck();
        cdc.setRand(rand);

        for (int i = 0; i < possibleImmediateEffects.size(); i++) {
            rand.setResult(i);
            cc = cdc.getTop();
            ImmediateEffect[] ie = new ImmediateEffect[i % 3 + 1];
            for (int j = 0; j < (i % 3 + 1); j++) {
                ie[j] = possibleImmediateEffects.get(i);
            }
            assert cc.isEmpty() || Arrays.equals(cc.get().immediateEffect(), ie);
        }
    }
}
