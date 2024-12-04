package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.CivilisationCard;

import java.util.Optional;

public class CivilizationCardDeckTest {
    @Test
    public void getTopTest(){
        CivilizationCardDeck cdc = new CivilizationCardDeck();
        for (int i = 0; i < 100; i++){
            Optional<CivilisationCard> card = cdc.getTop();
            if (i < 36){
                assert card.isPresent();
            }
            else{
                assert card.isEmpty();
            }
        }
    }
}
