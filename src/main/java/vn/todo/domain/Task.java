package vn.todo.domain;

import org.hibernate.validator.constraints.SafeHtml;
import vn.todo.View;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Task extends AbstractBaseEntity {

    @Column(name = "title", nullable = false)
    @NotBlank
    @SafeHtml(groups = {View.ValidatedRestUI.class})
    private String title;

    @Column(name = "complete", nullable = false, columnDefinition = "bool default false")
    private boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    @NotNull(groups = View.Persist.class)
    private Todo todo;

    public Task() {
    }

    public Task(String title) {
        this(title, false);
    }

    public Task(String title, boolean isComplete) {
        this(null, title, isComplete);
    }

    public Task(Integer id, String title, boolean isComplete) {
        super(id);
        this.title = title;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
