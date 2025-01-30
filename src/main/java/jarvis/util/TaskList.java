package jarvis.util;

import jarvis.task.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
        System.out.println("Marked as done: " + tasks.get(index));
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        System.out.println("Marked as not done: " + tasks.get(index));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
