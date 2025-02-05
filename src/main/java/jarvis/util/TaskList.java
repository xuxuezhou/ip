package jarvis.util;

import jarvis.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods for managing them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a TaskList with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return tasks.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // 返回任务列表的副本，防止外部修改内部数据
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markTask(int index) {
        validateIndex(index);
        tasks.get(index).markAsDone();
        System.out.println("Marked as done: " + tasks.get(index));
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        validateIndex(index);
        tasks.get(index).markAsNotDone();
        System.out.println("Marked as not done: " + tasks.get(index));
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets a specific task from the list.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Validates whether the given index is within bounds.
     *
     * @param index The index to check.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
    }

    /**
     * Prints the list of tasks.
     */
    public void printTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
