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
        System.out.println("Hello, sir. I am Jarvis, now with enhanced task management.");
        System.out.println("Commands:");
        System.out.println("  - 'todo <task>' → Adds a ToDo task");
        System.out.println("  - 'deadline <task> /by <date>' → Adds a Deadline task");
        System.out.println("  - 'event <task> /from <start> /to <end>' → Adds an Event task");
        System.out.println("  - 'mark <number>' → Marks a task as done");
        System.out.println("  - 'unmark <number>' → Unmarks a completed task");
        System.out.println("  - 'list' → Shows all tasks");
        System.out.println("  - 'bye' → Exits the program");
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
            } else if (userInput.startsWith("todo ")) {
                addToDo(userInput.substring(5));
            } else if (userInput.startsWith("deadline ")) {
                addDeadline(userInput);
            } else if (userInput.startsWith("event ")) {
                addEvent(userInput);
            } else if (userInput.startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.startsWith("unmark ")) {
                unmarkTask(userInput);
            } else {
                System.out.println("Sir, I did not understand that command. Please use 'todo', 'deadline', 'event', 'mark', or 'unmark'.");
            }
        }

        scanner.close();
    }

    private static void addToDo(String taskDescription) {
        tasks[taskCount++] = new ToDo(taskDescription);
        printAddedTask();
    }

    private static void addDeadline(String input) {
        try {
            String[] parts = input.split(" /by ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new Exception();
            }
            String description = parts[0].substring("deadline ".length()).trim();
            String by = parts[1].trim();
            tasks[taskCount++] = new Deadline(description, by);
            printAddedTask();
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid deadline format: 'deadline <task> /by <date>'.");
        }
    }

    private static void addEvent(String input) {
        try {
            String[] parts = input.split(" /from | /to ");
            if (parts.length < 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new Exception();
            }
            String description = parts[0].substring("event ".length()).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            tasks[taskCount++] = new Event(description, from, to);
            printAddedTask();
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid event format: 'event <task> /from <start> /to <end>'.");
        }
    }

    private static void markTask(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks[taskNumber].markAsDone();
            System.out.println("_________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber]);
            System.out.println("_________________________________________");
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number for marking.");
        }
    }

    private static void unmarkTask(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks[taskNumber].markAsNotDone();
            System.out.println("_________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber]);
            System.out.println("_________________________________________");
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number for unmarking.");
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

    private static void printAddedTask() {
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("_________________________________________");
    }
}
