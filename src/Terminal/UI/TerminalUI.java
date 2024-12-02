package Terminal.UI;

import Terminal.Terminal;

public class TerminalUI {
    private final Terminal terminal;

    public TerminalUI(Terminal terminal) {
        this.terminal = terminal;
    }

    public void processInput(String input) {
        try {
            for (char ch : input.toCharArray()) {
                terminal.enterPin(ch);
            }
            System.out.println("PIN accepted. You are now authenticated.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
