package jarvis.util;

import jarvis.task.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a list of tasks and provides methods for managing them.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Set<String> taskSet; // Track unique task descriptions

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskSet = new HashSet<>();
    }

    /**
     * Initializes a TaskList with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.taskSet = new HashSet<>();
        for (Task task : tasks) {
            taskSet.add(task.getDescription());
        }
    }

    /**
     * Adds a task to the list only if it does not already exist.
     *
     * @param task The task to be added.
     * @return true if the task was added, false if it was a duplicate.
     */
    public boolean addTask(Task task) {
        if (taskSet.contains(task.getDescription())) {
            return false; // Task already exists
        }
        tasks.add(task);
        taskSet.add(task.getDescription());
        return true;
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task deleteTask(int index) {
        validateIndex(index);
        Task removedTask = tasks.remove(index);
        taskSet.remove(removedTask.getDescription());
        return removedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        validateIndex(index);
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        validateIndex(index);
        tasks.get(index).markAsNotDone();
    }

    /**
     * Retrieves a specific task from the list.
     *
     * @param index The index of the task (0-based).
     * @return The task at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public Task getTask(int index) {
        validateIndex(index);
        return tasks.get(index);
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
     * Returns a copy of the current task list.
     *
     * @return A list of all tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks); // Return a copy to prevent modification from outside
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
     * Returns a formatted string representation of the task list.
     *
     * @return A string representation of the task list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }

        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
