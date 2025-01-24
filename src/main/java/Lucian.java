import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Lucian {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        System.out.println("____________________________________________________________");
        System.out.println("Hey! I'm Lucian\n" + "How can I help?");
        System.out.println("____________________________________________________________");


        while (scanner.hasNext()) {
            System.out.println("____________________________________________________________");
            try {
                String userInput = scanner.nextLine();
                String command = userInput.split(" ")[0];
                //String description = userInput.substring(command.length() + 1);
                //System.out.println(description);

                if (command.equals("bye")) {
                    System.out.println("Bye. See you around.");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        Task currentTask = listOfTasks.get(i);
                        System.out.println(i + 1 + "." + currentTask.toString());
                    }
                } else if (command.equals("mark") || command.equals("unmark")) {
                    if (userInput.split(" ").length == 1) {
                        throw new LucianException("You have to give me the index to mark/unmark...");
                    }
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    Task currentTask = listOfTasks.get(index - 1);
                    if (userInput.startsWith("mark")) {
                        currentTask.markAsDone();
                    } else {
                        currentTask.markAsNotDone();
                    }
                } else if (command.equals("delete")) {
                    if (userInput.split(" ").length == 1) {
                        throw new LucianException("You have to give me the index to delete...");
                    }
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    Task removedTask = listOfTasks.get(index - 1);
                    System.out.println("Sure, I'll remove the following task:");
                    System.out.println(removedTask);
                    listOfTasks.remove(index - 1);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    if (userInput.split(" ").length == 1) {
                        throw new LucianException("The description of a " + command + " cannot be empty man...");
                    }
                    Task newTask = createTask(userInput);
                    System.out.println("Roger. I'll be adding this task to the list:");
                    System.out.println(newTask);
                    listOfTasks.add(newTask);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                } else {
                    throw new LucianException("You did not give me a valid command...");
                }

            } catch (LucianException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }

    private static Task createTask(String input) {
        Task createdTask;
        String description;
        if (input.startsWith("todo ")) {
            description = input.substring(5);
            createdTask = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by");
            String by = input.substring(byIndex + 4);
            description = input.substring(9, byIndex - 1);
            createdTask = new Deadline(description, by);
        } else {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            description = input.substring(6, fromIndex - 1);
            createdTask = new Event(description, from, to);
        }
        return createdTask;
    }
}
