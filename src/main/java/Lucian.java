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
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                System.out.println("Bye. See you around.");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < listOfTasks.size(); i++) {
                    Task currentTask = listOfTasks.get(i);
                    System.out.println(i + 1 + "." + currentTask.toString());
                }
            } else if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                String index = userInput.split(" ")[1];
                int idx = Integer.parseInt(index);
                Task currentTask = listOfTasks.get(idx - 1);
                if (userInput.startsWith("mark")) {
                    currentTask.markAsDone();
                } else {
                    currentTask.markAsNotDone();
                }
            } else if (userInput.startsWith("todo ") || userInput.startsWith("deadline ") || userInput.startsWith("event ")) {
                Task newTask = createTask(userInput);
                System.out.println("Roger. I'll be adding this task to the list:");
                System.out.println(newTask);
                listOfTasks.add(newTask);
                System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
            } else {
                System.out.println("I can't understand what your saying, try telling me something I can understand...");
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
            description = input.substring(6, byIndex - 1);
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
