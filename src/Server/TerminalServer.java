package Server;

import Exception.InsufficientFundsException;
public class TerminalServer {
    private int balance;

    public TerminalServer(int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    /**
     * Добавляет указанную сумму к балансу.
     * @param amount сумма для добавления.
     */
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    /**
     * Уменьшает баланс на указанную сумму.
     * @param amount сумма для снятия.
     * @throws InsufficientFundsException если на счёте недостаточно средств.
     */
    public void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds on the account. Current balance: " + balance);
        }
        balance -= amount;
    }

    /**
     * Возвращает текущий баланс.
     * @return текущий баланс.
     */
    public int getBalance() {
        return balance;
    }
}
