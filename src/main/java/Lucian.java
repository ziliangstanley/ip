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
        ArrayList<String> listOfInputs = new ArrayList<String>();

        System.out.println("____________________________________________________________");
        System.out.println("Hey! I'm Lucian\n" + "How can I help?");
        System.out.println("____________________________________________________________");


        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. See you around.");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < listOfInputs.size(); i++) {
                    System.out.println(i+1 + ". " + listOfInputs.get(i));
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                listOfInputs.add(userInput);
                System.out.println("____________________________________________________________");
            }
        }

    }
}
