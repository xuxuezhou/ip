package jarvis.util;

import jarvis.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_shouldIncreaseSize() {
        taskList.addTask(new ToDo("Test Task"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void deleteTask_shouldRemoveCorrectTask() {
        ToDo task1 = new ToDo("Task 1");
        ToDo task2 = new ToDo("Task 2");

        taskList.addTask(task1);
        taskList.addTask(task2);

        taskList.deleteTask(0);
        assertEquals(1, taskList.getSize());
        assertEquals("Task 2", taskList.getTask(0).getDescription());
    }

    @Test
    void getSize_shouldReturnCorrectSize() {
        assertEquals(0, taskList.getSize());
        taskList.addTask(new ToDo("Task A"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void getTask_shouldReturnCorrectTask() {
        ToDo task = new ToDo("Important Task");
        taskList.addTask(task);
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    void getTask_shouldThrowExceptionForInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(0));
    }
}
