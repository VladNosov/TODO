package vn.todo.model;

import java.util.List;

public class ToDoList extends BaseEntity {

    private String title;
    private List<Task> tasks;

    public ToDoList(String title) {
        this(null, title);
    }

    public ToDoList(Integer id, String title) {
        super(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}