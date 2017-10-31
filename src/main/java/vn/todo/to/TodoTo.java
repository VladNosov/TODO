package vn.todo.to;

import javax.validation.constraints.NotBlank;

public class TodoTo extends BaseTo {
    @NotBlank
    private String title;

    public TodoTo() {
    }

    public TodoTo(Integer id, String title) {
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
        return "TodoTo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}