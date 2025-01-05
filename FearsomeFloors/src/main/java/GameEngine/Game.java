package GameEngine;
import GameEngine.Actor.Monster;
import GameEngine.Actor.Player;
import GameEngine.Actor.TokenCapacityNotFoundException;
import GameEngine.IOSystem.IInputOutputStream;
import GameEngine.World.Board;

import java.util.ArrayList;

public class Game {
    public static final int MaxPlayersNumber = 7;

    private Board board;
    private ArrayList<Player> players;
    private Monster monster;
    private IInputOutputStream inputOutputStream;

    public Game(
            int playersNumber,
            int boardDx,
            int boardDy,
            IInputOutputStream inputReader)
            throws MaxPlayersReachedException, TokenCapacityNotFoundException {
        if (playersNumber > MaxPlayersNumber) {
            throw new MaxPlayersReachedException(playersNumber);
        }

        var tokensPerPlayer = playersNumber > 3 ? 3 : 4;

        board = new Board(boardDx, boardDy);
        players = new ArrayList<>();
        monster = new Monster(10,10, 20, board);
        this.inputOutputStream = inputReader;

        for (int i = 0; i < playersNumber; i++) {
            players.add(new Player("Player " + (i + 1), tokensPerPlayer, board));
        }
    }

    public void start() {
        while (!isGameOver()) {
            for (Player currentPlayer : players)
            {
                currentPlayer.takeTurn(inputOutputStream);

                if (currentPlayer.hasWon()) {
                    inputOutputStream.infoMessage(currentPlayer.getName() + " has won the game!");
                    break;
                }
            }

            monster.takeTurn(inputOutputStream);
        }
    }

    private boolean isGameOver() {
        return players.stream().allMatch(Player::hasWon);
    }
}