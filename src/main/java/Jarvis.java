import java.util.Scanner;

public class Jarvis {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = "      ____.                  .__        \n"
                + "     |    |____ __________  _|__| ______\n"
                + "     |    \\__  \\_  __ \\  \\/ /  |/  ___/\n"
                + " /\\__|    |/ __ \\|  | \\/\\   /|  |\\___ \\ \n"
                + " \\________(____  /__|    \\_/ |__/____  >\n"
                + "               \\/                    \\/ \n";

        System.out.println(logo);
        System.out.println("_________________________________________");
        System.out.println("Hello, sir. I am Jarvis, your loyal AI assistant.");
        System.out.println("What task would you like me to handle?");
        System.out.println("Type 'list' to see stored tasks or 'bye' to shut me down.");
        System.out.println("_________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print("> ");
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("_________________________________________");
                System.out.println("Very well, sir. Shutting down. Until next time.");
                System.out.println("_________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("_________________________________________");
            System.out.println("Added: " + task);
            System.out.println("_________________________________________");
        } else {
            System.out.println("Apologies, sir. My storage capacity has reached its limit.");
        }
    }

    private static void displayTasks() {
        System.out.println("_________________________________________");
        if (taskCount == 0) {
            System.out.println("Sir, it appears you have no pending tasks.");
        } else {
            System.out.println("Here is the list of your tasks, sir:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("_________________________________________");
    }
}
