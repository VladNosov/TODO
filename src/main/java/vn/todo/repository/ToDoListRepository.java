package vn.todo.repository;

import vn.todo.domain.Todo;
import java.util.List;

public interface ToDoListRepository {
    Todo save(Todo todo, int userId);

    boolean delete(int id, int userId);

    Todo get(int id, int userId);

    List<Todo> getAll(int userId);
}