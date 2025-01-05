package GameEngine.Actor;
import GameEngine.IOSystem.IInputOutputStream;
import GameEngine.Movement.MovementDirection;
import GameEngine.World.Board;
import GameEngine.Movement.StraightMove;
import java.util.List;
import java.util.ArrayList;

public class Player {
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

    public void takeTurn(IInputOutputStream inputOutputStream) {
        int tokenIndex = 0;
        inputOutputStream.infoMessage("C'est le tour du joueur " + getName() + "!");

        do
        {
            PlayerToken token = tokens.get(tokenIndex);
            int numberOfStraightMoves = inputOutputStream.readInput(
                    "Donner les mouvements droit pour le token "+ tokenIndex,
                    Integer.class);

            var straightMoves = new ArrayList<StraightMove>();

            for(int moveIndex =0; moveIndex < numberOfStraightMoves; moveIndex++)
            {
                int directionCode = inputOutputStream.readInput(
                        "Donner la direction du movement " + (moveIndex+1) + "pour le token " + (tokenIndex+1),
                        Integer.class);

                var cases = inputOutputStream.readInput(
                        "Donner le nombre de case pour le mouvement " + (moveIndex+1),
                        Integer.class);

                straightMoves.add(new StraightMove(MovementDirection.fromCode(directionCode),
                        cases));
            }

            try {
                token.move(straightMoves);
                board.printBoard();
                tokenIndex++;
            }
            catch (Exception e)
            {
                inputOutputStream.errorMessage(e.getMessage());
            }
        }
        while( tokenIndex < tokens.size());
    }

    public boolean hasWon() {
        return tokens.stream().allMatch(PlayerToken::isAtExit);
    }
}