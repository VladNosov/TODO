package vn.todo.service;

import vn.todo.model.ToDoList;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface ToDoListService {
    ToDoList update(ToDoList toDoList, int userId) throws NotFoundException;

    ToDoList save(ToDoList toDoList, int userId);

    void delete(int id, int userId) throws NotFoundException;

    ToDoList get(int id, int userId) throws NotFoundException;

    List<ToDoList> getAll(int userId);
}