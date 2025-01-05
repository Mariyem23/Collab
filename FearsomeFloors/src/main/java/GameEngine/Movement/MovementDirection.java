package GameEngine.Movement;

public enum MovementDirection {
    X_Up(4),
    X_Down(6),
    Y_Up(8),
    Y_Down(5);

    private final int code;

    MovementDirection(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    // Static method to convert int to enum
    public static MovementDirection fromCode(int code) {
        for (MovementDirection direction: MovementDirection.values()) {
            if (direction.code == code) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}