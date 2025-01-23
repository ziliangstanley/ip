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
            } else if (userInput.startsWith("mark")) {
                String index = userInput.split(" ")[1];
                int idx = Integer.parseInt(index);
                Task currentTask = listOfTasks.get(idx - 1);
                currentTask.markAsDone();
            } else if (userInput.startsWith("unmark")) {
                String index = userInput.split(" ")[1];
                int idx = Integer.parseInt(index);
                Task currentTask = listOfTasks.get(idx - 1);
                currentTask.markAsNotDone();
            } else {
                System.out.println("added: " + userInput);
                Task newTask = new Task(userInput);
                listOfTasks.add(newTask);
            }
            System.out.println("____________________________________________________________");
        }

    }
}
