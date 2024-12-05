package sk.uniba.fmph.dcs.stone_age;

import sk.uniba.fmph.dcs.game_board.*;
import sk.uniba.fmph.dcs.game_phase_controller.GamePhaseControllerFactory;
import sk.uniba.fmph.dcs.player_board.PlayerBoard;
import sk.uniba.fmph.dcs.player_board.PlayerBoardFactory;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class StoneAgeGameFactory {
    private final static int FOOD_IN_HUNTING_GROUNDS = 153;
    private final static int WOOD_IN_FOREST = 28;
    private final static int CLAY_IN_CLAY_MOUND = 18;
    private final static int STONE_IN_QUARRY = 12;
    private final static int GOLD_IN_RIVER = 10;

    private StoneAgeGameFactory() {}

    public static StoneAgeGame createDefaultStoneAgeGame(int numberOfPlayers){
        if (numberOfPlayers < 2 || numberOfPlayers > 4){
            throw new RuntimeException("Invalid number of players");
        }

        PlayerOrder po = new PlayerOrder(0, numberOfPlayers);
        HashMap<Integer, PlayerOrder> playersMap = new HashMap<>();
        ArrayList<Player> playersList = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++){
            playersList.add(new Player(po, PlayerBoardFactory.createDefaultPlayerBoard().getValue()));
            playersMap.put(i, po);
            po = po.forward();
        }
        StoneAgeObservable stoneAgeObservable = new StoneAgeObservable();

        ArrayList<Effect> effects = new ArrayList<>(List.of(Effect.FOOD, Effect.WOOD, Effect.CLAY, Effect.STONE,
                Effect.GOLD, Effect.FIELD, Effect.BUILDING, Effect.TOOL, Effect.ONE_TIME_TOOL2, Effect.ONE_TIME_TOOL3,
                Effect.ONE_TIME_TOOL4));
        InterfaceTakeReward takeReward = new RewardMenu(effects, playersList);

        HashMap<PlayerOrder, InterfaceFeedTribe> feedTribe = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++){
            feedTribe.put(po, new PlayerBoardGameBoardFacade((PlayerBoard) playersList.get(i).playerBoard()));
            po = po.forward();
        }

        HashMap<Location, InterfaceFigureLocation> places = new HashMap<>();
        InterfaceFigureLocationInternal figureLocationInternal = new BuildingTile(new ArbitraryBuilding(2));
        InterfaceFigureLocation buildingTile1 = new FigureLocationAdaptor(figureLocationInternal, playersList);
        InterfaceFigureLocation buildingTile2 = new FigureLocationAdaptor(figureLocationInternal, playersList);
        InterfaceFigureLocation buildingTile3 = new FigureLocationAdaptor(figureLocationInternal, playersList);
        InterfaceFigureLocation buildingTile4 = new FigureLocationAdaptor(figureLocationInternal, playersList);
        places.put(Location.BUILDING_TILE1, buildingTile1);
        places.put(Location.BUILDING_TILE2, buildingTile2);
        places.put(Location.BUILDING_TILE3, buildingTile3);
        places.put(Location.BUILDING_TILE4, buildingTile4);

        CivilizationCardDeck cdc = new CivilizationCardDeck();
        GetCard getCard = new GetCard(cdc);
        InterfaceFigureLocationInternal civilisationCard1Internal = new CivilizationCardPlace(getCard);
        InterfaceFigureLocation civCard1 = new FigureLocationAdaptor(civilisationCard1Internal, playersList);
        InterfaceFigureLocationInternal civilisationCard2Internal = new CivilizationCardPlace(getCard);
        InterfaceFigureLocation civCard2 = new FigureLocationAdaptor(civilisationCard2Internal, playersList);
        InterfaceFigureLocationInternal civilisationCard3Internal = new CivilizationCardPlace(getCard);
        InterfaceFigureLocation civCard3 = new FigureLocationAdaptor(civilisationCard3Internal, playersList);
        InterfaceFigureLocationInternal civilisationCard4Internal = new CivilizationCardPlace(getCard);
        InterfaceFigureLocation civCard4 = new FigureLocationAdaptor(civilisationCard4Internal, playersList);
        places.put(Location.CIVILISATION_CARD1, civCard1);
        places.put(Location.CIVILISATION_CARD2, civCard2);
        places.put(Location.CIVILISATION_CARD3, civCard3);
        places.put(Location.CIVILISATION_CARD4, civCard4);

        InterfaceFigureLocationInternal huntingGroundsInternal = new ResourceSource(Effect.FOOD,FOOD_IN_HUNTING_GROUNDS);
        InterfaceFigureLocation huntingGrounds = new FigureLocationAdaptor(huntingGroundsInternal, playersList);
        places.put(Location.HUNTING_GROUNDS, huntingGrounds);

        InterfaceFigureLocationInternal forestInternal = new ResourceSource(Effect.WOOD, WOOD_IN_FOREST);
        InterfaceFigureLocation forest = new FigureLocationAdaptor(forestInternal, playersList);
        places.put(Location.FOREST, forest);

        InterfaceFigureLocationInternal clayMoundInternal = new ResourceSource(Effect.CLAY, CLAY_IN_CLAY_MOUND);
        InterfaceFigureLocation clayMound = new FigureLocationAdaptor(clayMoundInternal, playersList);
        places.put(Location.CLAY_MOUND, clayMound);

        InterfaceFigureLocationInternal quarryInternal = new ResourceSource(Effect.STONE, STONE_IN_QUARRY);
        InterfaceFigureLocation quarry = new FigureLocationAdaptor(quarryInternal, playersList);
        places.put(Location.QUARRY, quarry);

        InterfaceFigureLocationInternal riverInternal = new ResourceSource(Effect.GOLD, GOLD_IN_RIVER);
        InterfaceFigureLocation river = new FigureLocationAdaptor(riverInternal, playersList);
        places.put(Location.RIVER, river);

        ToolMakerHutsFields tmhf = new ToolMakerHutsFields(numberOfPlayers);
        InterfaceFigureLocationInternal fieldsInternal = new PlaceOnFieldsAdaptor(tmhf);
        InterfaceFigureLocation fields = new FigureLocationAdaptor(fieldsInternal, playersList);
        places.put(Location.FIELD, fields);

        InterfaceFigureLocationInternal hutInternal = new PlaceOnHutAdaptor(tmhf);
        InterfaceFigureLocation hut = new FigureLocationAdaptor(hutInternal, playersList);
        places.put(Location.HUT, hut);

        InterfaceFigureLocationInternal toolmakerInternal = new PlaceOnToolMakerAdaptor(tmhf);
        InterfaceFigureLocation toolmaker = new FigureLocationAdaptor(toolmakerInternal, playersList);
        places.put(Location.TOOL_MAKER, toolmaker);

        HashMap<PlayerOrder, InterfaceNewTurn> playerBoardMap = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++){
            playerBoardMap.put(po, (PlayerBoardGameBoardFacade) playersList.get(i).playerBoard());
            po = po.forward();
        }

        HashMap<PlayerOrder, InterfaceToolUse> toolUse = new HashMap<>();
        CurrentThrow ct = new CurrentThrow();
        for (int i = 0; i < numberOfPlayers; i++){
            toolUse.put(po, ct);
            po = po.forward();
        }

        InterfaceGamePhaseController gamePhaseController = GamePhaseControllerFactory.createGamePhaseController(takeReward, feedTribe, places, playerBoardMap, toolUse, po);

        HashMap<Integer, InterfaceGetState> playerBoardStates = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++){
            playerBoardStates.put(i, (InterfaceGetState) playersList.get(i).playerBoard());
        }

        return new StoneAgeGame(playersMap, stoneAgeObservable, gamePhaseController, playerBoardStates, GameBoardFactory.createGameBoard(playersList));
    }
}
