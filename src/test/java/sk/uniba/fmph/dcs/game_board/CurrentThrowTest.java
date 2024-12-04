package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.player_board.PlayerBoard;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class CurrentThrowTest {
    @Test
    public void useToolTest(){
        CurrentThrow ct = new CurrentThrow();
        PlayerOrder po = new PlayerOrder(0, 4);
        PlayerBoard pb = new PlayerBoard();
        for (int i = 0; i < 7; i++){
            pb.getPlayerTools().addTool();
            pb.newTurn();
        }

        Player p1 = new Player(po, new PlayerBoardGameBoardFacade(pb));


        for (int i = 0; i < 50; i++) {
            ct.initiate(p1, Effect.FOOD, 5);
            int oldRes = ct.getThrowsResult();
            assert oldRes >= 2 && oldRes <= 15;

            ct.useTool(0);

            assert oldRes + 1 <= ct.getThrowsResult();

            assert pb.getPlayerTools().getToolStrength(0) == 0;

            pb.newTurn();
        }
    }

    @Test
    public void canUseToolsTest(){
        CurrentThrow ct = new CurrentThrow();
        PlayerOrder po = new PlayerOrder(0, 4);
        PlayerBoard pb = new PlayerBoard();
        for (int i = 0; i < 7; i++){
            pb.getPlayerTools().addTool();
            pb.newTurn();
        }

        Player p1 = new Player(po, new PlayerBoardGameBoardFacade(pb));
        ct.initiate(p1, Effect.FOOD, 5);

        assert ct.canUseTools();

        pb.getPlayerTools().useTool(0);

        assert ct.canUseTools();

        pb.getPlayerTools().useTool(1);

        assert ct.canUseTools();

        pb.getPlayerTools().useTool(2);

        assert ! ct.canUseTools();

        pb.newTurn();

        assert ct.canUseTools();
    }

    @Test
    public void finishUsingToolsTest(){
        CurrentThrow ct = new CurrentThrow();
        PlayerOrder po = new PlayerOrder(0, 4);
        PlayerBoard pb = new PlayerBoard();
        for (int i = 0; i < 7; i++){
            pb.getPlayerTools().addTool();
            pb.newTurn();
        }

        Player p1 = new Player(po, new PlayerBoardGameBoardFacade(pb));
        ct.initiate(p1, Effect.FOOD, 5);

        assert  !ct.finishUsingTools();

        pb.getPlayerTools().useTool(0);

        assert  !ct.finishUsingTools();

        pb.getPlayerTools().useTool(1);

        assert  !ct.finishUsingTools();

        pb.getPlayerTools().useTool(2);

        assert  ct.finishUsingTools();

        pb.newTurn();

        assert !ct.finishUsingTools();
    }
}
