package vn.todo.repository;

import vn.todo.model.Task;
import java.util.Collection;

public interface TaskRepository {
    Task save(Task task);

    void delete(int id);

    Task get(int id);

    Collection<Task> getAll();
}