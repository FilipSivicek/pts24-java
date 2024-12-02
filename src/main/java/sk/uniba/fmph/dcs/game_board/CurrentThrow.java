package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.InterfaceToolUse;

public final class CurrentThrow implements InterfaceToolUse {
    private final Effect throwsFor;
    private final int throwsResult;
    private Player player;
    private Effect effect;
    private int dices;

    public CurrentThrow(final Effect throwsFor, final int throwResult) {
        this.throwsFor = throwsFor;
        this.throwsResult = throwResult;
    }

    public void initiate(final Player player, final Effect effect, final int dices) {
        this.player = player;
        this.effect = effect;
        this.dices = dices;
    }

    @Override
    public boolean useTool(final int idx) {
        return player.playerBoard().useTool(idx).isPresent();
    }

    @Override
    public boolean canUseTools() {
        return player.playerBoard().hasSufficientTools(1);
    }

    @Override
    public boolean finishUsingTools() {
        return !player.playerBoard().hasSufficientTools(1);
    }
}
