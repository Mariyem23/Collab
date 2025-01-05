package GameEngine;

public class MaxPlayersReachedException extends Exception {

    public MaxPlayersReachedException(int currentPlayerNumber)
    {
        super("Trying to set a game with" + currentPlayerNumber +
                "The max number of players allowed is" + Game.MaxPlayersNumber + ".");
    }
}