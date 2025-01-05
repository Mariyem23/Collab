package GameEngine.World;
import GameEngine.World.Tile;
import GameEngine.World.TileType;

public class Board {
    private Tile[][] grid;

    public Board(int dx, int dy) {
        grid = new Tile[dx][dy];

        // Initialize the grid with empty tiles
        for (int x = 0; x < dx; x++) {
            for (int y = 0; y < dy; y++) {
                grid[x][y] = new Tile(x, y, TileType.EMPTY);
            }
        }

        // Example obstacles and exit
        grid[5][5] = new Tile(5, 5, TileType.WALL);
        grid[0][9] = new Tile(0, 9, TileType.EXIT);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return null; // Out of bounds
        }
        return grid[x][y];
    }

    public void printBoard() {
        for (Tile[] ly : grid) {
            for (Tile tile : ly) {
                System.out.print(tile.getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public boolean isClearPath(int x1,int y1,int x2,int y2)
    {
        return true;
    }
}