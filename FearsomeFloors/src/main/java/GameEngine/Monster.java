package GameEngine;
import java.util.Random;
import java.util.List;

class Monster {
    private Board board;
    private int x, y;
    private int visionRange;

    public Monster(int x, int y, int visionRange, Board board) {
        this.x = x;
        this.y = y;
        this.visionRange = visionRange;
        this.board = board;
    }

    public void takeTurn() {
        // Simple logic: move randomly (replace with line-of-sight logic)
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

        System.out.println("Monster moved to (" + x + ", " + y + ")");
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