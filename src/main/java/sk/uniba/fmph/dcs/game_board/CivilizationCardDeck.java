package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.ImmediateEffect;

import java.util.*;

public final class CivilizationCardDeck {
    int givenOutCards = 0;
    int totalCards = 36;
    RandomInterface rand = new Rand();
    ArrayList<ImmediateEffect> possibleImmediateEffects = new ArrayList<>(
            List.of(ImmediateEffect.POINT, ImmediateEffect.WOOD, ImmediateEffect.CLAY, ImmediateEffect.STONE,
                    ImmediateEffect.GOLD, ImmediateEffect.FOOD));

    ArrayList<EndOfGameEffect> possibleEndOfGameEffects = new ArrayList<>(List.of(EndOfGameEffect.FARMER,
            EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.BUILDER, EndOfGameEffect.SHAMAN, EndOfGameEffect.MEDICINE,
            EndOfGameEffect.ART, EndOfGameEffect.MUSIC, EndOfGameEffect.WRITING, EndOfGameEffect.SUNDIAL,
            EndOfGameEffect.POTTERY, EndOfGameEffect.TRANSPORT, EndOfGameEffect.WEAVING));

    public Optional<CivilisationCard> getTop() {
        if (givenOutCards >= totalCards) {
            return Optional.empty();
        }
        ArrayList<ImmediateEffect> immediateEffects = new ArrayList<>();
        int amountOfImmediate = rand.randomInt(3) + 1;
        for (int i = 0; i < amountOfImmediate; i++) {
            immediateEffects.add(possibleImmediateEffects.get(rand.randomInt(possibleImmediateEffects.size())));
        }

        EndOfGameEffect[] endOfGameEffects = new EndOfGameEffect[1];
        endOfGameEffects[0] = possibleEndOfGameEffects.get(rand.randomInt(possibleEndOfGameEffects.size()));
        CivilisationCard card = new CivilisationCard(immediateEffects.toArray(new ImmediateEffect[0]),
                endOfGameEffects);
        givenOutCards++;
        return Optional.of(card);
    }

    public void setRand(RandomInterface rand) {
        this.rand = rand;
    }

    public String state() {
        return givenOutCards + "";
    }
}
