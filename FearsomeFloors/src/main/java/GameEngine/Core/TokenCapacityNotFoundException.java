package GameEngine.Core;

public class TokenCapacityNotFoundException extends Exception {
    public TokenCapacityNotFoundException(int tokenIndex)
    {
        super("Capacity for token with index " + tokenIndex +
                " Not found");
    }
}