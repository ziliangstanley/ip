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
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hey! I'm lucian.Lucian\nHow can I help?");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. See you around.");
    }

    /**
     * Prints a separator line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
