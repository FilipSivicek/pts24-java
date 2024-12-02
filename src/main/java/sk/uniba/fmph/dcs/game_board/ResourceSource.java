package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.HashSet;

public class ResourceSource implements InterfaceFigureLocationInternal {
    private String name = "";
    private final Effect resource;
    private int count;
    private final int maxFigures = 7;
    private int maxFigureColors = 4;

    private HashSet<PlayerOrder> playersPlaced = new HashSet<>();

    private ArrayList<PlayerOrder> figures = new ArrayList<>();

    public ResourceSource(final Effect resource, final int count) {
        this.resource = resource;
        this.count = count;
    }


    public ResourceSource(final Effect resource, final int count, final String name) {
        this.resource = resource;
        this.count = count;
        this.name = name;
    }

    /**
     * TODO.
     *
     * @param player
     * @param figureCount
     *
     * @return TODO
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (player.playerOrder().getPlayers() == 3){
            maxFigureColors = 2;
        }
        else if (player.playerOrder().getPlayers() == 2){
            maxFigureColors = 1;
        }

        if (figures.size() + figureCount > maxFigures){
            return false;
        }

        if (playersPlaced.size() >= maxFigureColors && !playersPlaced.contains(player.playerOrder())){
            return false;
        }

        playersPlaced.add(player.playerOrder());
        for (int i = 0; i < figureCount; i++){
            figures.add(player.playerOrder());
        }

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
        if (player.playerOrder().getPlayers() == 3){
            maxFigureColors = 2;
        }
        else if (player.playerOrder().getPlayers() == 2){
            maxFigureColors = 1;
        }

        if (figures.size() + count > maxFigures){
            return HasAction.NO_ACTION_POSSIBLE;
        }

        if (playersPlaced.size() >= maxFigureColors && !playersPlaced.contains(player.playerOrder())){
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
        int size = figures.size();
        for (int i = size-1; i >= 0; i--){
            if (figures.get(i).getOrder() == player.playerOrder().getOrder()){
                figures.remove(i);
            }
        }
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
        return false;
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
        for (PlayerOrder figure : figures) {
            if (figure.getOrder() == player.playerOrder().getOrder()) {
                return HasAction.WAITING_FOR_PLAYER_ACTION;
            }
        }
        return HasAction.NO_ACTION_POSSIBLE;
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public boolean newTurn() {
        if (!figures.isEmpty()){
            return false;
        }
        playersPlaced = new HashSet<>();
        return true;
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public String state() {
        return name + " " + resource + " " + count;
    }
}
