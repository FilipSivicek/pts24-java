package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.List;
import java.util.Random;

public final class GameBoardFactory {
    private GameBoardFactory(){}

    public static GameBoard createGameBoard(List<Player> players){
        Building[] buildings = new Building[28];
        Random rand = new Random();
        Effect[] buildingsCost = new Effect[]{Effect.STONE, Effect.CLAY, Effect.WOOD, Effect.GOLD};
        for (int i = 0; i < 28; i++){
            int randResult = rand.nextInt();
            if (randResult%3 == 0){
                int amount = rand.nextInt()%4 + 1;
                Effect[] cost = new Effect[amount];
                for (int j = 0; j < amount; j++){
                    cost[j] = buildingsCost[rand.nextInt()%buildingsCost.length];
                }
                buildings[i] = new SimpleBuilding(List.of(cost));
            }
            else if (randResult%3 == 1){
                buildings[i] = new ArbitraryBuilding(rand.nextInt()%6 + 1);
            }
            else {
                buildings[i] = new VariableBuilding(rand.nextInt()%7 + 2, rand.nextInt()%3 + 1);
            }
        }

        return new GameBoard(players, buildings);
    }
}
