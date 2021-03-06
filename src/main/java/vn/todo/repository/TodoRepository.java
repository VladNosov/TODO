package vn.todo.repository;

import vn.todo.domain.Todo;
import java.util.List;

public interface TodoRepository {
    Todo save(Todo todo, int userId);

    boolean delete(int id, int userId);

    Todo get(int id, int userId);

    List<Todo> getAll(int userId);

    default Todo getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}