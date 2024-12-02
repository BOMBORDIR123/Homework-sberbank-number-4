package Exception;

//Этот класс может быть использован, чтобы обрабатывать неправильный ввод пользовател
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
