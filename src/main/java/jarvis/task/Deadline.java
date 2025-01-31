package jarvis.task;

public class Deadline extends Task {
    protected String by;  // 任务截止日期

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // 添加 getter 方法
    public String getBy() {
        return by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
