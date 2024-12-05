package sk.uniba.fmph.dcs.game_board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniba.fmph.dcs.player_board.PlayerBoardFactory;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class CivilizationCardPlaceTest {
    CivilizationCardDeck cdc;
    CivilizationCardPlace place;
    PlayerBoardGameBoardFacade p1boardFacade;
    PlayerOrder po;
    Player player;

    @BeforeEach
    public void setUp(){
        cdc = new CivilizationCardDeck();
        place = new CivilizationCardPlace(cdc);
        p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        po = new PlayerOrder(0, 4);
        player = new Player(po, p1boardFacade);

    }


    @Test
    public void placeFiguresTest(){
        CivilizationCardDeck cdc = new CivilizationCardDeck();
        CivilizationCardPlace place = new CivilizationCardPlace(cdc);
        PlayerBoardGameBoardFacade p1boardFacade = PlayerBoardFactory.createDefaultPlayerBoard().getValue();
        PlayerOrder po = new PlayerOrder(0, 4);
        Player player = new Player(po, p1boardFacade);

        boolean ans = place.placeFigures(player, 2);
        assert !ans;

        ans = place.placeFigures(player, 1);
        assert ans;

        ans = place.placeFigures(player, 1);
        assert !ans;

        place.newTurn();
        ans = place.placeFigures(player, 1);
        assert ans;
    }

    @Test
    public void tryToPlaceFiguresTest(){
        HasAction ans = place.tryToPlaceFigures(player, 2);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        ans = place.tryToPlaceFigures(player, 1);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        place.placeFigures(player, 1);

        ans = place.tryToPlaceFigures(player, 1);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        place.newTurn();
        ans = place.tryToPlaceFigures(player, 1);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Test
    public void makeActionTest(){
        place.placeFigures(player, 1);
        ActionResult ans = place.makeAction(new Player(new PlayerOrder(1, 4), PlayerBoardFactory.createDefaultPlayerBoard().getValue()), null, null);
        assert ans == ActionResult.FAILURE;

        ans = place.makeAction(player, null, null);
        assert ans == ActionResult.ACTION_DONE;

        ans = place.makeAction(player, null, null);
        assert ans == ActionResult.FAILURE;

        place.newTurn();
        place.placeFigures(player, 1);
        ans = place.makeAction(player, null, null);
        assert ans == ActionResult.ACTION_DONE;
    }

    @Test
    public void skipActionTest(){
        boolean ans = place.skipAction(player);
        assert !ans;

        place.placeFigures(player, 1);
        ans = place.skipAction(player);
        assert ans;

        ans = place.skipAction(new Player(new PlayerOrder(1, 4), PlayerBoardFactory.createDefaultPlayerBoard().getValue()));
        assert !ans;
    }

    @Test
    public void tryToMakeActionTest(){
        place.placeFigures(player, 1);
        HasAction ans = place.tryToMakeAction(new Player(new PlayerOrder(1, 4), PlayerBoardFactory.createDefaultPlayerBoard().getValue()));
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        ans = place.tryToMakeAction(player);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
        place.makeAction(player, null, null);

        ans = place.tryToMakeAction(player);
        assert ans == HasAction.NO_ACTION_POSSIBLE;

        place.newTurn();
        place.placeFigures(player, 1);
        ans = place.tryToMakeAction(player);
        assert ans == HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Test
    public void newTurnTest(){
        boolean ans;
        for (int i = 0; i < 35; i++){
            place.placeFigures(player, 1);
            place.makeAction(player, null, null);
            ans = place.newTurn();
            assert !ans;
        }

        place.placeFigures(player, 1);
        place.makeAction(player, null, null);

        for (int i = 0; i < 100; i++){
            ans = place.newTurn();
            assert ans;
        }
    }
}
