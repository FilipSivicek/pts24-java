package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.HasAction;

import java.util.ArrayList;

public class CivilizationCardPlace implements InterfaceFigureLocationInternal {
    private int requiredResources;
    private CivilisationCard card;
    private ArrayList<PlayerOrder> figures;

    public CivilizationCardPlace(CivilisationCard card){
        this.card = card;
    }

    public CivilizationCardPlace(){}

    /**
     * TODO.
     *
     * @param player
     * @param figureCount
     *this.card = null;

     * @return TODO
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (!figures.isEmpty() || figureCount > 1){
            return false;
        }
        figures.add(player.playerOrder());
        return true;
    }

    /**
     * TODO.
     *
     * @param player
     * @param count
     *
     * @return TODO
     */
    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        if (!figures.isEmpty() || count > 1){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    /**
     * TODO.
     *
     * @param player
     * @param inputResources
     * @param outputResources
     *
     * @return TODO
     */
    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder()){
            return ActionResult.FAILURE;
        }
        figures.removeFirst();
        return ActionResult.ACTION_DONE;
    }

    /**
     * TODO.
     *
     * @param player
     *
     * @return TODO
     */
    @Override
    public boolean skipAction(final Player player) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder()){
            return false;
        }
        figures.removeFirst();
        return true;
    }

    /**
     * TODO.
     *
     * @param player
     *
     * @return TODO
     */
    @Override
    public HasAction tryToMakeAction(final Player player) {
        if (figures.isEmpty() || figures.getFirst().getOrder() != player.playerOrder().getOrder()){
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public boolean newTurn() {
        return !figures.isEmpty();
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public String state() {
        return "";
    }
}
