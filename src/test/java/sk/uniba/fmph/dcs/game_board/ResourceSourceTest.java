package sk.uniba.fmph.dcs.game_board;

import org.junit.Before;
import org.junit.Test;
import sk.uniba.fmph.dcs.player_board.PlayerBoardFactory;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class ResourceSourceTest {
    PlayerBoardGameBoardFacade p1boardFacade;
    PlayerBoardGameBoardFacade p2boardFacade;
    PlayerBoardGameBoardFacade p3boardFacade;
    PlayerBoardGameBoardFacade p4boardFacade;
    ResourceSource source4;
    PlayerOrder po4;
    Player p1;
    Player p2;
    Player p3;
    Player p4;


    @Before
    public void setUp(){
        source4 = new ResourceSource(Effect.WOOD, 27);
        po4 = new PlayerOrder(0 ,4);

        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p3boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p4boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();

        p1 = new Player(po4, p1boardFacade);
        po4 = po4.forward();
        p2 = new Player(po4, p2boardFacade);
        po4 = po4.forward();
        p3 = new Player(po4, p3boardFacade);
        po4 = po4.forward();
        p4 = new Player(po4, p4boardFacade);
        po4 = po4.forward();

    }

    @Test
    public void placeFigureTest(){

        boolean ans = source4.placeFigures(p1, 2);
        assert ans;

        ans = source4.placeFigures(p2, 7);
        assert !ans;

        ans = source4.placeFigures(p3, 5);
        assert ans;

        ans = source4.placeFigures(p4, 0);
        assert ans;

        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p3boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();

        PlayerOrder po3 = new PlayerOrder(0, 3);
        p1 = new Player(po3, p1boardFacade);
        po3 = po3.forward();
        p2 = new Player(po3, p2boardFacade);
        po3 = po3.forward();
        p3 = new Player(po3, p3boardFacade);

        ResourceSource source3 = new ResourceSource(Effect.CLAY, 18);
        ans = source3.placeFigures(p1, 2);
        assert ans;

        ans = source3.placeFigures(p2, 3);
        assert ans;

        ans = source3.placeFigures(p3, 2);
        assert !ans;

        ans = source3.placeFigures(p1, 2);
        assert ans;

        ans = source3.placeFigures(p2, 1);
        assert !ans;

        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerOrder po2 = new PlayerOrder(0, 2);

        p1 = new Player(po2, p1boardFacade);
        po2 = po2.forward();
        p2 = new Player(po2, p2boardFacade);
        ResourceSource source2 = new ResourceSource(Effect.STONE, 15);

        ans = source2.placeFigures(p1, 3);
        assert ans;

        ans = source2.placeFigures(p2, 2);
        assert !ans;

        ans = source2.placeFigures(p1, 4);
        assert ans;
    }


    @Test
    public void tryToPlaceFigureTest(){
        HasAction ans = source4.tryToPlaceFigures(p1, 2);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source4.placeFigures(p1, 2);

        ans = source4.tryToPlaceFigures(p2, 7);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        ans = source4.tryToPlaceFigures(p3, 5);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source4.placeFigures(p3, 5);

        ans = source4.tryToPlaceFigures(p4, 0);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;

        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p3boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();

        PlayerOrder po3 = new PlayerOrder(0, 3);
        p1 = new Player(po3, p1boardFacade);
        po3 = po3.forward();
        p2 = new Player(po3, p2boardFacade);
        po3 = po3.forward();
        p3 = new Player(po3, p3boardFacade);

        ResourceSource source3 = new ResourceSource(Effect.CLAY, 18);
        ans = source3.tryToPlaceFigures(p1, 2);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source3.placeFigures(p1, 2);

        ans = source3.tryToPlaceFigures(p2, 3);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source3.placeFigures(p2, 3);

        ans = source3.tryToPlaceFigures(p3, 2);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        ans = source3.tryToPlaceFigures(p1, 2);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source3.placeFigures(p1, 2);

        ans = source3.tryToPlaceFigures(p2, 1);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        p2boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerOrder po2 = new PlayerOrder(0, 2);

        p1 = new Player(po2, p1boardFacade);
        po2 = po2.forward();
        p2 = new Player(po2, p2boardFacade);
        ResourceSource source2 = new ResourceSource(Effect.STONE, 15);

        ans = source2.tryToPlaceFigures(p1, 3);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source2.placeFigures(p1, 3);

        ans = source2.tryToPlaceFigures(p2, 2);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        ans = source2.tryToPlaceFigures(p1, 4);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        source2.placeFigures(p1, 4);
    }

    @Test
    public void makeActionTest(){
        ActionResult ans = source4.makeAction(p1, null, null);
        assert  ans == ActionResult.FAILURE;

        source4.placeFigures(p2, 5);
        ans = source4.makeAction(p2, null, null);
        assert ans == ActionResult.ACTION_DONE;

        source4.placeFigures(p3, 7);
        ans = source4.makeAction(p3, null, null);
        assert  ans == ActionResult.ACTION_DONE;

        source4.placeFigures(p4, 10);
        ans = source4.makeAction(p4, null, null);
        assert ans == ActionResult.FAILURE;
    }

    @Test
    public void tryToMakeActionTest(){
        HasAction ans = source4.tryToMakeAction(p1);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        source4.placeFigures(p2, 2);
        ans = source4.tryToMakeAction(p2);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;

        ans = source4.tryToMakeAction(p3);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        source4.placeFigures(p4, 10);
        ans = source4.tryToMakeAction(p4);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        source4.placeFigures(p4, 1);
        ans = source4.tryToMakeAction(p4);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}