package jarvis.util;

import jarvis.task.Task;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private static final String TEST_FILE = "test_tasks.txt";
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        // 删除测试文件，确保每次运行测试都是独立的
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void load_shouldReturnEmptyListIfFileDoesNotExist() throws IOException {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Expected empty list when file does not exist.");
    }

    @Test
    void saveAndLoad_shouldPreserveTaskList() throws IOException {
        // 准备任务列表
        List<Task> tasks = List.of(
                new ToDo("Buy groceries"),
                new Deadline("Submit report", "2024-02-01"),
                new Event("Team meeting", "10:00", "12:00")
        );

        // 保存任务
        storage.save(tasks);

        // 重新加载
        List<Task> loadedTasks = storage.load();

        // 确保任务数匹配
        assertEquals(3, loadedTasks.size(), "Task list size should be preserved");

        // 确保任务内容一致
        assertEquals("Buy groceries", loadedTasks.get(0).getDescription());
        assertEquals("Submit report", loadedTasks.get(1).getDescription());
        assertEquals("2024-02-01", ((Deadline) loadedTasks.get(1)).getBy());
        assertEquals("Team meeting", loadedTasks.get(2).getDescription());
        assertEquals("10:00", ((Event) loadedTasks.get(2)).getFrom());
        assertEquals("12:00", ((Event) loadedTasks.get(2)).getTo());
    }

    @Test
    void load_shouldMarkCompletedTasksCorrectly() throws IOException {
        String content = "T | 1 | Completed Task\nD | 0 | Incomplete Deadline | 2024-02-10\n";
        Files.writeString(new File(TEST_FILE).toPath(), content);

        List<Task> tasks = storage.load();

        assertEquals(2, tasks.size());
        assertTrue(tasks.get(0).isDone(), "Completed task should be marked as done.");
        assertFalse(tasks.get(1).isDone(), "Incomplete task should not be marked as done.");
    }
}
