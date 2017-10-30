package vn.todo.util;

import vn.todo.domain.Task;
import vn.todo.to.TaskTo;

public class TaskUtil {
    public static Task createNewFromTo(TaskTo newTask) {
        return new Task(newTask.getTitle(), false);
    }

    public static Task updateFromTo(Task task, TaskTo taskTo) {
        task.setTitle(taskTo.getTitle());
        return task;
    }
}