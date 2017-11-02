package vn.todo.to;

import org.hibernate.validator.constraints.SafeHtml;
import vn.todo.View;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TaskTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 120)
    @SafeHtml(groups = {View.ValidatedRestUI.class})
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