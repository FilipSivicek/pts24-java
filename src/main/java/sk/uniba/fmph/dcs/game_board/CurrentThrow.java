package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.InterfaceToolUse;

import java.util.Arrays;

public final class CurrentThrow implements InterfaceToolUse {
    private Effect throwsFor;
    private int throwsResult = 0;
    private Player player;
    private int dices;

    public void initiate(final Player player, final Effect effect, final int dices){
        this.player = player;
        this.throwsFor = effect;
        this.dices = dices;
        RandomInterface dicesThrow = new Throw();
        throwsResult = Arrays.stream(dicesThrow.randomArray(this.dices)).sum();
    }

    public void initiate(final Player player, final Effect effect, final int dices, RandomInterface randomInterface) {
        this.player = player;
        this.throwsFor = effect;
        this.dices = dices;
        throwsResult = Arrays.stream(randomInterface.randomArray(this.dices)).sum();
    }

    @Override
    public boolean useTool(final int idx) {
        throwsResult += player.playerBoard().getToolStrength(idx);
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

    public int getThrowsResult(){
        return throwsResult/throwsFor.points();
    }
}
