package vn.todo.service;

import vn.todo.domain.Todo;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface ToDoListService {
    Todo update(Todo todo, int userId) throws NotFoundException;

    Todo create(Todo todo, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Todo get(int id, int userId) throws NotFoundException;

    List<Todo> getAll(int userId);
}