package vn.todo.model;

import java.util.List;

public class User extends NamedEntity {
    private String email;
    private String password;
    private boolean enabled = true;
    private List<ToDoList> toDoLists;

    public User() {
    }

    public User(Integer id, String name, String email, String password) {
        this(id, name, email, password, true);
    }

    public User(Integer id, String name, String email, String password, boolean enabled) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}