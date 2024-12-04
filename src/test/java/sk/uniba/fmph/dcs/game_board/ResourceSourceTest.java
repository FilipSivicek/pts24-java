package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.player_board.PlayerBoardFactory;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class ResourceSourceTest {

    @Test
    public void placeFigureTest(){
        ResourceSource source4 = new ResourceSource(Effect.WOOD, 27);
        ResourceSource source3 = new ResourceSource(Effect.CLAY, 18);
        ResourceSource source2 = new ResourceSource(Effect.STONE, 15);
        PlayerOrder po4 = new PlayerOrder(0 ,4);

        PlayerBoardGameBoardFacade p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerBoardGameBoardFacade p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerBoardGameBoardFacade p3boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerBoardGameBoardFacade p4boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();

        Player p1 = new Player(po4, p1boardFacade);
        po4 = po4.forward();
        Player p2 = new Player(po4, p2boardFacade);
        po4 = po4.forward();
        Player p3 = new Player(po4, p3boardFacade);
        po4 = po4.forward();
        Player p4 = new Player(po4, p4boardFacade);
        po4 = po4.forward();

        boolean ans = source4.placeFigures(p1, 2);
        assert ans;

        ans = source4.placeFigures(p2, 7);
        assert !ans;

        ans = source4.placeFigures(p3, 5);
        assert ans;

        ans = source2.placeFigures(p4, 0);
        assert ans;

        
    }
}
