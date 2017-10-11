package vn.todo.repository;

import vn.todo.model.ToDoList;
import java.util.List;

public interface ToDoListRepository {
    ToDoList save(ToDoList toDoList, int userId);

    boolean delete(int id, int userId);

    ToDoList get(int id, int userId);

    List<ToDoList> getAll(int userId);
}