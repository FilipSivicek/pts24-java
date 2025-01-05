package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public class CivilizationCardPlace implements InterfaceFigureLocationInternal {
    private int requiredResources;
    private int figure = -1;
    private GetCard getCard;

    public CivilizationCardPlace(GetCard getCard){
        this.getCard = getCard;
    }

    public CivilizationCardPlace(){}

    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (figure > -1 || figureCount > 1){
            return false;
        }
        figure = player.playerOrder().getOrder();
        return true;
    }

    @Override
    public HasAction tryToPlaceFigures(final Player player, final int figureCount) {
        if (figure > -1 || figureCount > 1){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        if (figure != player.playerOrder().getOrder()){
            return ActionResult.FAILURE;
        }
        figure = -1;
        getCard.performEffect(player,null);
        return ActionResult.ACTION_DONE;
    }

    @Override
    public boolean skipAction(final Player player) {
        if (figure != player.playerOrder().getOrder()){
            return false;
        }
        figure = -1;
        return true;
    }

    @Override
    public HasAction tryToMakeAction(final Player player) {
        if (figure != player.playerOrder().getOrder()){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public boolean newTurn() {
        figure = -1;
        return getCard.getTakenCards() >= 36;
    }

    @Override
    public String state() {
        return requiredResources + "";
    }
}
