package vn.todo.service;

import vn.todo.model.Task;
import java.util.List;

public interface TaskService {
    /**
     * @return null if updated task do not belong to toDoListId
     */
    Task save(Task task, int toDoListId);

    /**
     * @return false if task do not belong to toDoListId
     */
    boolean delete(int id, int toDoListId);

    /**
     * @return null if task do not belong to toDoListId
     */
    Task get(int id, int toDoListId);

    /**
     * @return task ordered by id and isComplete;
     */
    List<Task> getAll(int toDoListId);
}