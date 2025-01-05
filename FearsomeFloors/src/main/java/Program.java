/*
Main entry point.
By EL MOUAYNI Meryiem. All wright reserved.
*/
import GameEngine.Game;
import GameEngine.IOSystem.ConsoleInputOutputStream;
import GameEngine.IOSystem.IInputOutputStream;

public class Program {
    public static void main(String[] args) {
        System.out.println("Fearsome Floor game!");

        IInputOutputStream inputOutputStream = new ConsoleInputOutputStream();
        try
        {
            Game game = new Game(
                    3,
                    10,
                    10,
            inputOutputStream);
            game.start();
        }
        catch (Exception e)
        {
            inputOutputStream.errorMessage("Exception when game started : " + e);
        }
    }
}
