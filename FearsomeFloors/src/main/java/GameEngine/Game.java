package GameEngine;
import GameEngine.Core.TokenCapacityNotFoundException;

import java.util.ArrayList;

public class Game {
    public static final int MaxPlayersNumber = 7;

    private Board board;
    private ArrayList<Player> players;
    private Monster monster;

    public Game(int playersNumber, int boardDx, int boardDy)
            throws MaxPlayersReachedException, TokenCapacityNotFoundException {
        if (playersNumber > MaxPlayersNumber) {
            throw new MaxPlayersReachedException(playersNumber);
        }

        var tokensPerPlayer = playersNumber > 3 ? 3 : 4;

        board = new Board(boardDx, boardDy);
        players = new ArrayList<>();
        monster = new Monster(10,10, 20, board);

        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("Player " + (i + 1), tokensPerPlayer, board));
        }
    }

    public void start() {
        while (!isGameOver()) {
            for (Player currentPlayer : players)
            {
                currentPlayer.takeTurn();
                if (currentPlayer.hasWon()) {
                    System.out.println(currentPlayer.getName() + " has won the game!");
                    break;
                }
            }

            monster.takeTurn();
        }
    }

    private boolean isGameOver() {
        return players.stream().allMatch(Player::hasWon);
    }
}