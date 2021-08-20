package cs2103t.duke.ui;

import java.util.Scanner;

/**
 * Represents a UI manager for Duke to manage front-end interactions with users.
 */
public class Ui {

    public static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    public static String divider = "    ____________________________________________________________";
    public static String space = "     ";
    public static String INVALID_INPUT = space + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private final Scanner scanner;

    /**
     * Constructs a UI manager for Duke.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user through this UI.
     */
    public void greet() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(space + "Hello! I'm Duke\n" + space + "What can I do for you?");
        System.out.println(divider + "\n");
    }

    /**
     * Displays a goodbye message to the user through this UI.
     */
    public void dismiss() {
        displayText(space + "Bye. Hope to see you again soon!");
    }

    /**
     * Reads one input line from the user.
     *
     * @return the one-line input from the user as a string.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Displays the specified error message to the user through this UI.
     *
     * @param message the error message to be displayed.
     */
    public void showError(String message) {
        displayText(message);
    }

    /**
     * Displays the specified text to the user through this UI.
     *
     * @param text the text to be displayed.
     */
    public void displayText(String text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider + "\n");
    }

}
