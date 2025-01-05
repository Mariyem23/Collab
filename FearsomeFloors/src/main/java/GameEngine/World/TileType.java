package GameEngine.World;

public enum TileType {
    EMPTY, WALL, EXIT, HAZARD;

    public String getSymbol() {
        return switch (this) {
            case WALL -> "#";
            case EXIT -> "E";
            case HAZARD -> "H";
            default -> ".";
        };
    }
}