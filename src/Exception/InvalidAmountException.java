package Exception;

//Это исключение может быть выброшено, если сумма для операции не кратна 100.
public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
