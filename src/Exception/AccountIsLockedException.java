package Exception;

//Это исключение можно выбрасывать, если пользователь пытается ввести PIN, когда аккаунт заблокирован.
public class AccountIsLockedException extends Exception {
    public AccountIsLockedException(String message) {
        super(message);
    }
}
