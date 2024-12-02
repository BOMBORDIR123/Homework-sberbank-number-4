package Terminal;

import Exception.InvalidAmountException;
import Exception.NotAuthenticatedException;
import Exception.InvalidInputException;
import Exception.AccountIsLockedException;
import Exception.InsufficientFundsException;

public interface Terminal {
    /**
     * Ввод символа PIN-кода.
     * @param digit символ для ввода (должен быть числом).
     * @throws InvalidInputException если введён нечисловой символ.
     * @throws AccountIsLockedException если аккаунт заблокирован.
     */
    void enterPin(char digit) throws InvalidInputException, AccountIsLockedException;

    /**
     * Положить деньги на счёт.
     * @param amount сумма для депозита (должна быть кратна 100).
     * @throws NotAuthenticatedException если пользователь не прошёл аутентификацию.
     * @throws InvalidAmountException если сумма не кратна 100.
     */
    void deposit(int amount) throws NotAuthenticatedException, InvalidAmountException;

    /**
     * Снять деньги со счёта.
     * @param amount сумма для снятия (должна быть кратна 100).
     * @throws NotAuthenticatedException если пользователь не прошёл аутентификацию.
     * @throws InvalidAmountException если сумма не кратна 100.
     * @throws InsufficientFundsException если недостаточно средств на счёте.
     */
    void withdraw(int amount) throws NotAuthenticatedException, InvalidAmountException, InsufficientFundsException;

    /**
     * Проверить баланс счёта.
     * @return текущий баланс.
     * @throws NotAuthenticatedException если пользователь не прошёл аутентификацию.
     */
    int checkBalance() throws NotAuthenticatedException;
}

