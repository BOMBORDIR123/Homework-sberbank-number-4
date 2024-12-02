package Exception;

//Это исключение можно использовать, чтобы обрабатывать попытки совершить операции, если пользователь не аутентифицирован.
public class NotAuthenticatedException extends Exception {
    public NotAuthenticatedException(String message) {
        super(message);
    }
}

