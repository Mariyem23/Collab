package GameEngine.IOSystem;

import java.util.Scanner;

public class ConsoleInputOutputStream implements IInputOutputStream {
    private final Scanner scanner;

    public ConsoleInputOutputStream() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public <T> T readInput(String prompt, Class<T> type) {
        System.out.print(prompt);

        String input = scanner.nextLine();

        try {
            if (type == Integer.class) {
                return type.cast(Integer.parseInt(input));
            } else if (type == Float.class) {
                return type.cast(Float.parseFloat(input));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(input));
            } else if (type == String.class) {
                return type.cast(input);
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid input for type " + type.getName() + ": " + input, e);
        }
    }

    @Override
    public void errorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void infoMessage(String message) {
        System.out.println(message);
    }
}