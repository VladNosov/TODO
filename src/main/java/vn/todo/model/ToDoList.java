package vn.todo.model;

public class ToDoList extends BaseEntity {

    private String title;

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

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}