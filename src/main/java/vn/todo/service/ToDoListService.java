package vn.todo.service;

import vn.todo.domain.ToDoList;
import vn.todo.util.exceptions.NotFoundException;
import java.util.List;

public interface ToDoListService {
    ToDoList update(ToDoList toDoList, int userId) throws NotFoundException;

    ToDoList create(ToDoList toDoList, int userId);

    void delete(int id, int userId) throws NotFoundException;

    ToDoList get(int id, int userId) throws NotFoundException;

    List<ToDoList> getAll(int userId);
}