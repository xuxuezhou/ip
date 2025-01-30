import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Jarvis {
    private static final String FILE_PATH = "./data/Jarvis.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile(); // 先加载任务

        System.out.println("      ____.                  .__        ");
        System.out.println("     |    |____ __________  _|__| ______");
        System.out.println("     |    \\__  \\_  __ \\  \\/ /  |/  ___/");
        System.out.println(" /\\__|    |/ __ \\|  | \\/\\   /|  |\\___ \\ ");
        System.out.println(" \\________(____  /__|    \\_/ |__/____  >");
        System.out.println("               \\/                    \\/ ");
        System.out.println("_________________________________________");
        System.out.println("Hello, sir. I am Jarvis, now with task persistence.");
        System.out.println("Commands:");
        System.out.println("  - 'todo <task>' → Adds a ToDo task");
        System.out.println("  - 'deadline <task> /by <date>' → Adds a Deadline task");
        System.out.println("  - 'event <task> /from <start> /to <end>' → Adds an Event task");
        System.out.println("  - 'mark <number>' → Marks a task as done");
        System.out.println("  - 'unmark <number>' → Unmarks a completed task");
        System.out.println("  - 'delete <number>' → Deletes a task");
        System.out.println("  - 'list' → Shows all tasks");
        System.out.println("  - 'bye' → Exits the program");
        System.out.println("_________________________________________");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("_________________________________________");
                System.out.println("Sir, I am now shutting down. Until next time.");
                System.out.println("_________________________________________");
                saveTasksToFile(); // 退出前保存任务
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
            } else if (userInput.startsWith("delete ")) {
                deleteTask(userInput);
            } else {
                System.out.println("Sir, I did not understand that command.");
            }
        }
        scanner.close();
    }

    private static void addToDo(String taskDescription) {
        tasks.add(new ToDo(taskDescription));
        printAddedTask();
        saveTasksToFile(); // 自动保存
    }

    private static void addDeadline(String input) {
        try {
            String[] parts = input.split(" /by ", 2);
            if (parts.length < 2) throw new Exception();
            tasks.add(new Deadline(parts[0].substring(9).trim(), parts[1].trim()));
            printAddedTask();
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println("Sir, please use 'deadline <task> /by <date>'.");
        }
    }

    private static void addEvent(String input) {
        try {
            String[] parts = input.split(" /from | /to ");
            if (parts.length < 3) throw new Exception();
            tasks.add(new Event(parts[0].substring(6).trim(), parts[1].trim(), parts[2].trim()));
            printAddedTask();
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println("Sir, please use 'event <task> /from <start> /to <end>'.");
        }
    }

    private static void markTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks.get(index).markAsDone();
            System.out.println("_________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            System.out.println("_________________________________________");
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number.");
        }
    }

    private static void unmarkTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            tasks.get(index).markAsNotDone();
            System.out.println("_________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index));
            System.out.println("_________________________________________");
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number.");
        }
    }

    private static void deleteTask(String command) {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            Task removedTask = tasks.remove(index);
            System.out.println("_________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks.");
            System.out.println("_________________________________________");
            saveTasksToFile();
        } catch (Exception e) {
            System.out.println("Sir, please provide a valid task number.");
        }
    }

    private static void displayTasks() {
        System.out.println("_________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("Sir, your task list is empty.");
        } else {
            System.out.println("Here are your tasks, sir:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("_________________________________________");
    }

    private static void printAddedTask() {
        System.out.println("_________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks.");
        System.out.println("_________________________________________");
    }

    private static void saveTasksToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // 确保目录存在
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" \\| ");
                Task task = switch (parts[0]) {
                    case "T" -> new ToDo(parts[2]);
                    case "D" -> new Deadline(parts[2], parts[3]);
                    case "E" -> new Event(parts[2], parts[3], parts[4]);
                    default -> null;
                };

                if (task != null && parts[1].equals("1")) task.markAsDone();
                if (task != null) tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting fresh.");
        }
    }
}
