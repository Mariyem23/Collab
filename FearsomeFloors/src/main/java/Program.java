/*
Main entry point.
By EL MOUAYNI Mariyem. All wright reserved.
*/
import GameEngine.Game;

public class Program {
    public static void main(String[] args) {
        System.out.println("Fearsome Floor game!");

        try
        {
            Game game = new Game(3, 10, 10);
            game.start();
        }
        catch (Exception e)
        {
            System.out.println("Exception when game started : " + e);
        }
    }
}
