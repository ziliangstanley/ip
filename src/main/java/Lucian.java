import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Lucian {
    private static final String FILE_NAME = "./data/tasks.txt";
    static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hey! I'm Lucian\n" + "How can I help?");
        System.out.println("____________________________________________________________");

        label:
        while (scanner.hasNext()) {
            System.out.println("____________________________________________________________");
            try {
                String userInput = scanner.nextLine();
                String command = userInput.split(" ")[0];

                switch (command) {
                case "bye":
                    saveTasksToFile();
                    System.out.println("Bye. See you around.");
                    System.out.println("____________________________________________________________");
                    break label;
                case "list":
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        Task currentTask = listOfTasks.get(i);
                        System.out.println(i + 1 + "." + currentTask.toString());
                    }
                    break;
                case "mark":
                case "unmark": {
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
                    break;
                }
                case "delete": {
                    if (userInput.split(" ").length == 1) {
                        throw new LucianException("You have to give me the index to delete...");
                    }
                    int index = Integer.parseInt(userInput.split(" ")[1]);
                    Task removedTask = listOfTasks.get(index - 1);
                    System.out.println("Sure, I'll remove the following task:");
                    System.out.println(removedTask);
                    listOfTasks.remove(index - 1);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                    break;
                }
                case "todo":
                case "deadline":
                case "event":
                    if (userInput.split(" ").length == 1) {
                        throw new LucianException("The description of a " + command + " cannot be empty man...");
                    }
                    Task newTask = createTask(userInput);
                    System.out.println("Roger. I'll be adding this task to the list:");
                    System.out.println(newTask);
                    listOfTasks.add(newTask);
                    System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new LucianException("You did not give me a valid command...");
                }

            } catch (LucianException | IOException e) {
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

    private static void saveTasksToFile() throws IOException {
        FileWriter writer = new FileWriter(FILE_NAME); // Overwrites the file

        for (Task task : listOfTasks) {
            writer.write(task.toFileFormat() + "\n");
        }

        writer.close(); // Important to close the writer
    }


    private static void loadTasksFromFile() {
        try {
            File directory = new File("./data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(FILE_NAME);
            Scanner scanner = new Scanner(fileReader); // Scanner to read line by line

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileFormat(line);
                listOfTasks.add(task);
            }

            scanner.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Error reading saved tasks.");
        }
    }

}
