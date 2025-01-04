package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public final class GetSomethingThrow implements EvaluateCivilisationCardImmediateEffect {
    private final Effect resource;
    private CurrentThrow currentThrow;
    private GameBoard gameBoard;
    private int throwResult = 0;

    public GetSomethingThrow(final Effect resource, GameBoard gameBoard) {
        this.resource = resource;
        this.gameBoard = gameBoard;
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (choice != this.resource) {
            return false;
        }

        ResourceSource rs = gameBoard.getResourceSource(choice);
        int playerFigures = 0;
        if (rs != null){
            playerFigures = rs.getFiguresCount(player);
        }


        currentThrow.initiate(player, choice, playerFigures);
        int pocet = currentThrow.getThrowsResult();
        Effect[] res = new Effect[pocet];
        Arrays.fill(res, choice);
        player.playerBoard().giveEffect(res);
        return true;
    }
}