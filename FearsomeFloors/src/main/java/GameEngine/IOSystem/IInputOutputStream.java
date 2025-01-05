package GameEngine.IOSystem;

public interface IInputOutputStream {
    <T> T readInput(String prompt, Class<T> type);
    void errorMessage(String message);
    void infoMessage(String message);
}