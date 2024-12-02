package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.ImmediateEffect;

import java.util.*;

public final class CivilizationCardDeck {
    Random rand = new Random();
    ArrayList<ImmediateEffect> possibleImmediateEffects = new ArrayList<>(List.of(ImmediateEffect.POINT,
            ImmediateEffect.WOOD, ImmediateEffect.CLAY, ImmediateEffect.STONE, ImmediateEffect.GOLD, ImmediateEffect.FOOD));
    ArrayList<EndOfGameEffect> possibleEndOfGameEffects = new ArrayList<>(List.of(EndOfGameEffect.FARMER, EndOfGameEffect.TOOL_MAKER,
            EndOfGameEffect.BUILDER, EndOfGameEffect.SHAMAN, EndOfGameEffect.MEDICINE, EndOfGameEffect.ART, EndOfGameEffect.MUSIC,
            EndOfGameEffect.WRITING, EndOfGameEffect.SUNDIAL, EndOfGameEffect.POTTERY, EndOfGameEffect.TRANSPORT, EndOfGameEffect.WEAVING));

    public Optional<CivilisationCard> getTop() {
        ArrayList<ImmediateEffect> immediateEffects = new ArrayList<>();
        int amountOfImmediate = rand.nextInt(3) + 1;
        for (int i = 0; i < amountOfImmediate; i++){
            immediateEffects.add(possibleImmediateEffects.get(rand.nextInt(possibleImmediateEffects.size())));
        }

        EndOfGameEffect[] endOfGameEffects = new EndOfGameEffect[1];
        endOfGameEffects[0] = possibleEndOfGameEffects.get(rand.nextInt(possibleEndOfGameEffects.size()));
        CivilisationCard card = new CivilisationCard(immediateEffects.toArray(new ImmediateEffect[0]), endOfGameEffects);
        return Optional.of(card);
    }

    public String state() {
        return "\n";
    }
}
