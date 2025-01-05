package GameEngine.Actor;

import GameEngine.World.Board;
import GameEngine.Movement.MovementDirection;
import GameEngine.Movement.StraightMove;
import GameEngine.World.TileType;

import java.util.List;

public abstract class Pawn {
    private int x, y;
    private Board board;

    public Pawn(Board board, int startX, int startY) {
        this.board = board;
        this.x = startX;
        this.y = startY;
    }

    public abstract void move(List<StraightMove> straightMoves) throws MaxPlayerTokenMovesException;

    public boolean isAtExit() {
        return board.getTile(x, y).getType() == TileType.EXIT;
    }

    private boolean canMoveTo(int newX, int newY) {
        return board.isClearPath(x, y, newX, newY);
    }

    private boolean canPushStone(int newX, int newY) {
        return board.isClearPath(newX, newY, newX + 1, newY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void doStraightMove(MovementDirection direction, int cases)
    {
        switch (direction)
        {
            case X_Up -> x = x + cases;
            case X_Down -> x = x - cases;
            case Y_Up -> y = y + cases;
            case Y_Down -> y = y - cases;
        }
    }
}