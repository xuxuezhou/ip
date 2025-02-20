package jarvis.util;

import jarvis.task.Task;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * Loads tasks from the file while ensuring no duplicates.
     *
     * @return A list of unique tasks read from the file.
     * @throws IOException If there is an error reading the file.
     */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        Set<String> taskSet = new HashSet<>();
        File file = new File(filePath);

        // 如果文件不存在，直接返回空列表，不创建新文件
        if (!file.exists()) {
            return tasks;
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
                }

                if (task != null && !taskSet.contains(task.getDescription())) {
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                    taskSet.add(task.getDescription());
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
        File file = new File(filePath);

        // 仅当文件已存在时才执行写入
        if (!file.exists()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        }
    }
}
