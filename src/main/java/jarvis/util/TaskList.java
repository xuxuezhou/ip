package jarvis.util;

import jarvis.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with predefined tasks.
     *
     * @param tasks A list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
        System.out.println("Marked as done: " + tasks.get(index));
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to unmark.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
        System.out.println("Marked as not done: " + tasks.get(index));
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets a specific task from the task list.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the list.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints all tasks in the list.
     */
    public void printTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
