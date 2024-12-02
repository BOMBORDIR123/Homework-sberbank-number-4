package Exception;

//Это исключение может быть выброшено, если на счету недостаточно средств для выполнения операции.
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}