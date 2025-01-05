package GameEngine.Actor;

public enum TokenMoveCapacity
{
    SixOne, FourThree, ThreeFour, TwoFive;

    public static TokenMoveCapacity getCapacityForToken(int tokenIndex) throws TokenCapacityNotFoundException
    {
        return switch (tokenIndex) {
            case 0 -> SixOne;
            case 1 -> FourThree;
            case 2 -> ThreeFour;
            case 3 -> TwoFive;
            default -> throw new TokenCapacityNotFoundException(tokenIndex);
        };
    }
}