package vn.todo.service;

import vn.todo.domain.Task;
import vn.todo.to.TaskTo;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface TaskService {
    Task create(Task task, int todoId);

    /**
     * @return null if updated task do not belong to todo
     */
    Task update(Task task, int todoId) throws NotFoundException;

    /**
     * @return null if updated task do not belong to todo
     */
    Task update(TaskTo task, int todoId) throws NotFoundException;

    /**
     * @return false if task do not belong to todo
     */
    void delete(int id, int todoId) throws NotFoundException;

    /**
     * @return null if task do not belong to todo
     */
    Task get(int id, int todoId) throws NotFoundException;

    /**
     * @return task ordered by id and isComplete;
     */
    List<Task> getAll(int todoId);

    Task getWithTodo(int id, int todoId);

    void complete(int todoId, int taskId, boolean enable);
}