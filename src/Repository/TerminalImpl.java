package Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


import Pin.PinValidator;
import Server.TerminalServer;
import Terminal.Terminal;
import Exception.InvalidAmountException;
import Exception.NotAuthenticatedException;
import Exception.InvalidInputException;
import Exception.AccountIsLockedException;
import Exception.InsufficientFundsException;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isAuthenticated = false;
    private StringBuilder enteredPin = new StringBuilder();
    private int incorrectPinAttempts = 0;
    private LocalDateTime lockUntil;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void enterPin(char digit) throws InvalidInputException, AccountIsLockedException {
        if (lockUntil != null && LocalDateTime.now().isBefore(lockUntil)) {
            long secondsLeft = ChronoUnit.SECONDS.between(LocalDateTime.now(), lockUntil);
            throw new AccountIsLockedException("Account is locked. Try again in " + secondsLeft + " seconds.");
        }

        if (!Character.isDigit(digit)) {
            System.out.println("Invalid input! Please enter only digits.");
            return;
        }

        enteredPin.append(digit);

        if (enteredPin.length() == 4) {
            if (pinValidator.validate(enteredPin.toString())) {
                isAuthenticated = true;
                enteredPin.setLength(0);
                incorrectPinAttempts = 0;
                System.out.println("PIN accepted. You are authenticated.");
            } else {
                incorrectPinAttempts++;
                enteredPin.setLength(0);

                if (incorrectPinAttempts >= 3) {
                    lockUntil = LocalDateTime.now().plusSeconds(10);
                    throw new AccountIsLockedException("Account locked due to multiple incorrect PIN attempts.");
                } else {
                    int attemptsLeft = 3 - incorrectPinAttempts;
                    throw new InvalidInputException("Incorrect PIN. Attempts left: " + attemptsLeft);
                }
            }
        }
    }


    @Override
    public void deposit(int amount) {
        try {
            validateAuthentication();
            if (amount % 100 != 0) {
                throw new InvalidAmountException("Deposit amount must be a multiple of 100.");
            }
            server.deposit(amount);
            System.out.println("Deposit successful. New balance: " + server.getBalance());
        } catch (NotAuthenticatedException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public void withdraw(int amount) {
        try {
            validateAuthentication();
            if (amount % 100 != 0) {
                throw new InvalidAmountException("Withdrawal amount must be a multiple of 100.");
            }
            server.withdraw(amount);
            System.out.println("Withdrawal successful. New balance: " + server.getBalance());
        } catch (NotAuthenticatedException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public int checkBalance() {
        try {
            validateAuthentication();
            int balance = server.getBalance();
            System.out.println("Current balance: " + balance);
            return balance;
        } catch (NotAuthenticatedException e) {
            System.out.println(e.getMessage());
            return 0;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return 0;
        }
    }


    private void validateAuthentication() throws NotAuthenticatedException {
        if (!isAuthenticated) {
            throw new NotAuthenticatedException("User is not authenticated. Please enter your PIN.");
        }
    }
}
