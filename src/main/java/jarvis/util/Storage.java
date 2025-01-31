package jarvis.util;

import jarvis.task.Task;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The file path to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks read from the file.
     * @throws IOException If there is an error reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist.
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        System.out.println("Warning: Unknown task type found in file.");
                }

                if (task != null && parts[1].equals("1")) {
                    task.markAsDone();
                }

                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        }
    }
}
