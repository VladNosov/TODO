package vn.todo.to;

import org.hibernate.validator.constraints.NotBlank;

public class TaskTo extends BaseTo {
    @NotBlank
    private String title;

    public TaskTo() {
    }

    public TaskTo(Integer id, String title) {
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
        return "TaskTo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}