package vn.todo.repository;

import vn.todo.domain.Task;
import java.util.List;

public interface TaskRepository {
    Task save(Task task, int todoId);

    boolean delete(int id, int todoId);

    Task get(int id, int todoId);

    List<Task> getAll(int todoId);

    default Task getWithTodo(int id, int todoId) {
        throw new UnsupportedOperationException();
    }
}