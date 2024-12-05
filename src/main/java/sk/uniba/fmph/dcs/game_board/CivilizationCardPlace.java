package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.HasAction;

import java.util.ArrayList;
import java.util.Optional;

public class CivilizationCardPlace implements InterfaceFigureLocationInternal {
    private int requiredResources;
    private CivilisationCard card;
    private ArrayList<PlayerOrder> figures = new ArrayList<>();
    private GetCard getCard;
    private boolean cardTaken = false;

    public CivilizationCardPlace(GetCard getCard){
        this.getCard = getCard;
    }

    public CivilizationCardPlace(){}

    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (!figures.isEmpty() || figureCount > 1){
            return false;
        }
        figures.add(player.playerOrder());
        return true;
    }

    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        if (!figures.isEmpty() || count > 1){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder() || cardTaken){
            return ActionResult.FAILURE;
        }
        figures.removeFirst();
        getCard.performEffect(player,null);
        return ActionResult.ACTION_DONE;
    }

    @Override
    public boolean skipAction(final Player player) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder()){
            return false;
        }
        figures.removeFirst();
        return true;
    }

    @Override
    public HasAction tryToMakeAction(final Player player) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder() || cardTaken){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    @Override
    public boolean newTurn() {
        figures = new ArrayList<>();
        return getCard.getTakenCards() >= 36;
    }

    @Override
    public String state() {
        return cardTaken + " " + requiredResources;
    }
}
