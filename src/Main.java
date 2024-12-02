import Pin.PinValidator;
import Repository.TerminalImpl;
import Server.TerminalServer;
import Terminal.Terminal;
import Exception.InvalidAmountException;
import Exception.NotAuthenticatedException;
import Terminal.UI.TerminalUI;
import URL.URLReader;

public static void main(String[] args) throws InterruptedException, NotAuthenticatedException, InvalidAmountException {
    URLReader reader = new URLReader();
    TerminalServer server = new TerminalServer(1000);
    PinValidator validator = new PinValidator("1234");
    Terminal terminal = new TerminalImpl(server, validator);

    try {
        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('0');
        terminal.enterPin('0');
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    try {
        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('5');
        terminal.enterPin('5');
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    try {
        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('3');
        terminal.enterPin('1');
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    Thread.sleep(1000);
    try {
        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('3');
        terminal.enterPin('1');
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    Thread.sleep(10000);
    try {
        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('3');
        terminal.enterPin('d');
        terminal.enterPin('4');
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    terminal.deposit(201);
    terminal.deposit(200);

    System.out.println(terminal.checkBalance());

    System.out.println("--------------------------------");

    System.out.println("Ввод URL:");

    reader.readContent("invalid-url");

    reader.readContent("https://www.example.com");

}
