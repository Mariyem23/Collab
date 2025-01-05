package GameEngine;

enum TileType {
    EMPTY, WALL, EXIT, HAZARD;

    public String getSymbol() {
        switch (this) {
            case WALL: return "#";
            case EXIT: return "E";
            case HAZARD: return "H";
            default: return ".";
        }
    }
}