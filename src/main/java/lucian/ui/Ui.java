package lucian.ui;

import java.util.Scanner;

/**
 * Handles user interaction, including reading input and displaying messages.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public String showWelcome() {
        return "Hey! I'm Lucian!\nHow can I help?";
    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. See you around.";
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public String showMessage(String message) {
        return message;
    }
}
