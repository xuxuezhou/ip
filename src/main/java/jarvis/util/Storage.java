package jarvis.util;

import jarvis.task.Task;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // 如果文件不存在，返回空列表
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                    case "T": // ToDo 任务
                        task = new ToDo(parts[2]);
                        break;
                    case "D": // Deadline 任务
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E": // Event 任务
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

    public void save(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
        }
    }
}
