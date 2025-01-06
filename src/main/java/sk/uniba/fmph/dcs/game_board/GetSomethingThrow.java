package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public final class GetSomethingThrow implements EvaluateCivilisationCardImmediateEffect {
    private final Effect resource;
    private CurrentThrow currentThrow = new CurrentThrow();
    private ResourceSource resourceSource;

    public GetSomethingThrow(final Effect resource, ResourceSource resourceSource) {
        this.resource = resource;
        this.resourceSource = resourceSource;
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (choice != this.resource) {
            return false;
        }

        int playerFigures = 0;
        if (resourceSource != null) {
            playerFigures = resourceSource.getFiguresCount(player);
        }

        currentThrow.initiate(player, choice, playerFigures);
        int amount = currentThrow.getThrowsResult();
        Effect[] res = new Effect[amount];
        Arrays.fill(res, choice);
        player.playerBoard().giveEffect(res);
        return true;
    }
}