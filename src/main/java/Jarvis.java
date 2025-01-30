import java.util.Scanner;

public class Jarvis {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
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
        System.out.println("Hello, sir. I am Jarvis, your personal assistant.");
        System.out.println("I now support task tracking. You may add tasks, list them, mark them as done, or unmark them.");
        System.out.println("Use commands: 'list', 'mark <number>', 'unmark <number>', or 'bye' to shut me down.");
        System.out.println("_________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.print("> ");
            userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("_________________________________________");
                System.out.println("Sir, I am now shutting down. Until next time.");
                System.out.println("_________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                displayTasks();
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("_________________________________________");
            System.out.println("Added: " + taskDescription);
            System.out.println("_________________________________________");
        } else {
            System.out.println("Sir, my memory is at full capacity. I cannot store more tasks.");
        }
    }

    private static void displayTasks() {
        System.out.println("_________________________________________");
        if (taskCount == 0) {
            System.out.println("Sir, your task list is currently empty.");
        } else {
            System.out.println("Here are your tasks, sir:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("_________________________________________");
    }

    private static void markTask(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                tasks[taskNumber].markAsDone();
                System.out.println("_________________________________________");
                System.out.println("Excellent choice, sir. I've marked this task as done:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println("_________________________________________");
            } else {
                System.out.println("Sir, that task does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number for marking.");
        }
    }

    private static void unmarkTask(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                tasks[taskNumber].markAsNotDone();
                System.out.println("_________________________________________");
                System.out.println("Understood, sir. I've marked this task as not done:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println("_________________________________________");
            } else {
                System.out.println("Sir, that task does not exist.");
            }
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number for unmarking.");
        }
    }
}
