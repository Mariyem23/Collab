package GameEngine;
import GameEngine.Core.StraightMove;
import GameEngine.Core.TokenCapacityNotFoundException;

import java.util.List;
import java.util.ArrayList;

class Player {
    private String name;
    private List<PlayerToken> tokens;
    private Board board;
    private int x, y; // Player's position on the grid

    public Player(String name, int numberOfToken, Board board) throws TokenCapacityNotFoundException {
        this.name = name;
        this.board = board;
        tokens = new ArrayList<>();

        for (int i = 0; i < numberOfToken; i++) {
            tokens.add(new PlayerToken(board, 0, 0, TokenMoveCapacity.getCapacityForToken(i)));
        }
    }

    public String getName() {
        return name;
    }

    public void moveToken(int tokenIndex, List<StraightMove> moves) throws MaxPlayerTokenMovesException {
        var token = tokens.get(tokenIndex);
        if(!token.isAtExit()) {
            token.move(moves);
        }
    }

    public void takeTurn()
    {
        // turn logic, with input of moves to perform for each token.
        for(PlayerToken token : tokens)
        {
            // read movement to place for each token.

        }
    }

    public boolean hasWon() {
        return tokens.stream().allMatch(PlayerToken::isAtExit);
    }


    // Move the player by a certain number of spaces
//    public boolean move(int steps) {
//        // Try to move the player
//        for (int i = 0; i < steps; i++) {
//            if (!canMoveTo(x + 1, y)) return false; // Check if the move is valid
//            x++;
//            if (grid.isStone(x, y)) {
//                if (!canPushStone(x + 1, y)) return false; // Check if stone can be pushed
//                grid.pushStone(x, y); // Push the stone
//            }
//        }
//        return true;
//    }
}