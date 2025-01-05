package GameEngine;

public class MaxPlayerTokenMovesException extends Exception
{
    public MaxPlayerTokenMovesException(int attemptedMoves, int maxMovesNumber)
    {
        super("Attempted moves " + attemptedMoves + " is above token capacity " +
               maxMovesNumber);
    }
}