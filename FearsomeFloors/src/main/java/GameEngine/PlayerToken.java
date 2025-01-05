package GameEngine;
import GameEngine.Core.Pair;
import GameEngine.Core.StraightMove;

import java.util.Map;
import java.util.List;

class PlayerToken extends Pawn {
    private int frontSideMoveCapacity, backSideMoveCapacity;
    private TokenSide currentSide = TokenSide.FrontSide;

    public static final Map<TokenMoveCapacity, Pair<Integer, Integer>> TokenCapacities =
            Map.of(
                    TokenMoveCapacity.SixOne,       new Pair(6,1),
                    TokenMoveCapacity.FourThree,    new Pair(4,3),
                    TokenMoveCapacity.TwoFive,      new Pair(2,5),
                    TokenMoveCapacity.ThreeFour,    new Pair(3,4));

    public PlayerToken(Board board, int startX, int startY, TokenMoveCapacity moveCapacity)
    {
        super(board, startX, startY);
        Pair<Integer, Integer> moveCapacities =  TokenCapacities.get(moveCapacity);
        this.frontSideMoveCapacity = moveCapacities.first();
        this.backSideMoveCapacity = moveCapacities.second();
    }

    @Override public void move(List<StraightMove> straightMoves)
            throws MaxPlayerTokenMovesException
    {
        int numOfElementaryMoves = straightMoves.stream()
                .mapToInt(StraightMove::cases)
                .reduce(0, Integer::sum);

        int movesCapacity = currentSide == TokenSide.FrontSide ?
                frontSideMoveCapacity : backSideMoveCapacity;

        if(numOfElementaryMoves > movesCapacity)
        {
            throw new MaxPlayerTokenMovesException(numOfElementaryMoves, movesCapacity);
        }

        for(StraightMove sMove : straightMoves)
        {
            doStraightMove(sMove.direction(), sMove.cases());
        }

        currentSide = currentSide == TokenSide.FrontSide ?
                TokenSide.BackSide : TokenSide.FrontSide;
    }
}