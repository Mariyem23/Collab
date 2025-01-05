package GameEngine.Actor;
import GameEngine.IOSystem.IInputOutputStream;
import GameEngine.World.Board;
import GameEngine.World.TileType;
import GameEngine.World.Tile;
import java.util.Random;
import java.util.List;

public class Monster {
    private Board board;
    private int x, y;
    private int visionRange;

    public Monster(int x, int y, int visionRange, Board board) {
        this.x = x;
        this.y = y;
        this.visionRange = visionRange;
        this.board = board;
    }

    public void takeTurn(IInputOutputStream inputOutputStream) {

        inputOutputStream.infoMessage("C'est le tour du monstre !");
        Random rand = new Random();

        int moveX = rand.nextInt(3) - 1; // Move -1, 0, or 1 column
        int moveY = rand.nextInt(3) - 1; // Move -1, 0, or 1 row
        int newX = x + moveX;
        int newY = y + moveY;


        Tile targetTile = board.getTile(newX, newY);
        if (targetTile != null && targetTile.getType() != TileType.WALL) {
            y = newY;
            x = newX;
        }

        inputOutputStream.infoMessage("Le monstre s'est déplacé à (" + x + ", " + y + ")");
        board.printBoard();
    }

    // Check if the monster can see a player (no obstacles between them)
    public boolean canSeePlayer(PlayerToken token) {
        int dx = Math.abs(x - token.getX());
        int dy = Math.abs(y - token.getY());
        if (dx > visionRange || dy > visionRange) return false;

        // Check if the line of sight is clear (no obstacles)
        return isLineOfSightClear(x, y, token.getX(), token.getY());
    }

    private boolean isLineOfSightClear(int x1, int y1, int x2, int y2) {
        return board.isClearPath(x1, y1, x2, y2);
    }

    public PlayerToken getClosestVisiblePlayer(List<PlayerToken> tokens) {
        PlayerToken closestToken = null;
        int minDistance = Integer.MAX_VALUE;

        for (PlayerToken token : tokens) {
            if (canSeePlayer(token)) {
                int distance = Math.abs(x - token.getX()) + Math.abs(y - token.getY());
                if (distance < minDistance) {
                    closestToken = token;
                    minDistance = distance;
                }
            }
        }

        return closestToken;
    }
}